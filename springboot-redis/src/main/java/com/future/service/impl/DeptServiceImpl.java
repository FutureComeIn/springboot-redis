package com.future.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import com.future.dao.DeptDao;
import com.future.entity.Dept;
import com.future.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private JSONObject json = new JSONObject();

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private JedisPool jedisPool;

    public List<Dept> selectDept() {
        Jedis jedis = jedisPool.getResource();
        String deptList = jedis.get("depts");
        /**
         * isBlank：判断某字符串是否为空 或 长度为0 或 由空白符(whitespace) 构成
         * isEmpty: 判断某字符串是否为空 或 长度为0 (不判断 空白符 为空)
         */
        if(!StringUtils.isBlank(deptList)){
            jedis.close();
            return json.parseArray(deptList, Dept.class);
        }
        List<Dept> resultList=deptDao.selectDept();
        jedis.set("depts",json.toJSONString(resultList));
        jedis.close();
        return resultList;
    }

    public Dept selectByNo(Long deptno) {
        Jedis jedis = jedisPool.getResource();
        String deptstr = jedis.hget("dept",deptno+"");
        if(!StringUtils.isBlank(deptstr)){
            jedis.close();
            return json.parseObject(deptstr,Dept.class);
        }
        Dept dept=deptDao.selectByNo(deptno);
        jedis.hset("dept",deptno+"",json.toJSONString(dept));
        jedis.close();
        return dept;
    }

    public Integer insert(Dept dept) {
        Jedis jedis = jedisPool.getResource();
        jedis.del("depts");
        jedis.close();
        return deptDao.insert(dept);
    }

    @Override
    public Integer update(Dept dept) {
        Jedis jedis = jedisPool.getResource();
        jedis.hdel("dept",dept.getDeptno()+"");
        jedis.del("depts");
        return deptDao.update(dept);
    }

}
