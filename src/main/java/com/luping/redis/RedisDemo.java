package com.luping.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDemo implements CommandLineRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    public  void setStr(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }


    @Override
    public void run(String... strings) throws Exception {
                          redisTemplate.opsForValue().set("a","a");
        Object a = redisTemplate.opsForValue().get("a");


        System.out.println(a);
    }
}
