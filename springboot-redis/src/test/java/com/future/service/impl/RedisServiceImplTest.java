package com.future.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.future.entity.Dept;
import com.future.entity.Person;
import com.future.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceImplTest {

    private JSONObject json = new JSONObject();

    @Autowired
    private RedisService redisService;

    // 插入字符串
    @Test
    public void setString() {
        redisService.set("test_str", "爱你");
    }

     // 获取字符串
    @Test
    public void getString() {
        System.out.println(redisService.get("test_str"));
    }

    // 插入对象
    @Test
    public void setObject() {
        Person person = new Person("zhangsan", "male");
        redisService.set("test_obj", json.toJSONString(person));
    }

    // 获取对象
    @Test
    public void getObject() {
        String result = redisService.get("test_obj");
        // String_json 转 Obj
        Dept person = json.parseObject(result, Dept.class);
        // Obj 转 String_json
        System.out.println(json.toJSONString(person));
    }

    /**
     * 插入对象List
     */
    @Test
    public void setList() {
        Person person1 = new Person("lisi", "male");
        Person person2 = new Person("wanger", "female");
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        redisService.set("test_obj_list", json.toJSONString(list));
    }

    /**
     * 获取list
     */
    @Test
    public void getList() {
        String result = redisService.get("test_obj_list");
        List<String> list = json.parseArray(result, String.class);
        System.out.println(list);
    }

    @Test
    public void remove() {
        redisService.remove("redis_test");
    }

    @Test
    public void testRedis(){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("http://localhost:6379");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        //清除当前数据库所有数据
        // jedis.flushDB();

        //设置键值对
        jedis.set("xiaohua","我是小花");
        //查看存储的键的总数
        System.out.println(jedis.dbSize());
        //取出设置的键值对并打印
        System.out.println(jedis.get("xiaohua"));
    }

}