package com.luping;

import com.luping.dao.UserMapper;
import com.luping.redis.RedisDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LupingApplicationTests {

    @Autowired
    RedisDemo redisDemo;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        redisDemo.setStr("a","a");

    }

    @Test
    public void contextDao() {
        List<Map> maps = userMapper.selectList();
        System.out.println(maps.toString());

    }


}
