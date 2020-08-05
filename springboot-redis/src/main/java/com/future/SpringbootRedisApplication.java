package com.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis缓存添加到业务逻辑:
 * redis作为缓存的作用:减少对数据库的访问压力，当访问数据首先从redis中查看是否有该数据，如果没有，则从数据库中读取,
 * 且将从数据库中读取的数据存放到缓存，下次再访问同样的数据时，还是先判断redis中是否存在该数据，如果有，则从缓存中读取，不访问数据库了
 * 存在的同步问题：如果对数据做了增删改，则在增删改的同时，同步更新redis的key-value
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})  配了datasource，不再需要
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }

}
