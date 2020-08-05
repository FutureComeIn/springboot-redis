package com.future.controller;

import com.future.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value="/get",method=RequestMethod.GET)
    public String get(String key){
        return redisService.get(key);
    }

    @RequestMapping(value="/getPool",method=RequestMethod.GET)
    public String getPool(String key){
        return redisService.getPool(key);
    }

    @RequestMapping(value="/setPool",method=RequestMethod.GET)
    public String setPool(String key,String value){
        return redisService.setPool(key,value);
    }

    @RequestMapping(value="/setExpirePool",method=RequestMethod.GET)
    public String setExpirePool(String key,int seconds,String value){
        return redisService.setExpirePool(key,seconds,value);
    }


}
