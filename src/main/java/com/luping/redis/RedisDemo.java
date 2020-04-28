package com.luping.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDemo {

    @Autowired
    private RedisTemplate redisTemplate;

    public  void setStr(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }
}
