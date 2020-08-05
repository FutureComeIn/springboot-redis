package com.future.service.impl;

import com.future.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    //RedisTemplate 不需要配置bean，jar包自动引入
    @Resource
    private RedisTemplate<String, ?> redisTemplate;

    @Autowired
    private JedisPool jedisPool;


    @Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    @Override
    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean remove(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(key.getBytes());
                return true;
            }
        });
        return result;
    }

    @Override
    public String getPool(String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    @Override
    public String setPool(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key, value);
        jedis.close();
        return str;
    }

    /**
     * 添加键值的同时，设置生命周期
     */
    @Override
    public String setExpirePool(String key, int seconds, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.setex(key, seconds, value);
        jedis.close();
        return str;
    }

    @Override
    public Long hsetPool(String hkey, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, field, value);
        jedis.close();
        return result;
    }

    @Override
    public String hgetPool(String hkey, String field) {
        Jedis jedis = jedisPool.getResource();
        String str=jedis.hget(hkey,field);
        jedis.close();
        return str;
    }
}
