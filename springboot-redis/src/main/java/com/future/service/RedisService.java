package com.future.service;

public interface RedisService {

    public boolean set(String key, String value);

    public String get(String key);

    public boolean expire(String key, long expire);

    public boolean remove(String key);

    public String getPool(String key);

    public String setPool(String key, String value);

    public String setExpirePool(String key, int seconds, String value);

    public Long hsetPool(String hkey,String field,String value);

    public String hgetPool(String hkey,String field);





}