package com.future.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Value("${spring.redis.host}")
    private  String host ;
    @Value("${spring.redis.port}")
    private  int port ;
    @Value("${spring.redis.timeout}")
    private  int timeout;
    @Value("${spring.redis.password}")
    private  String password ;
    @Value("${spring.redis.max-idle}")
    private  int maxIdle;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
