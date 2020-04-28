package com.luping.service;

import com.luping.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Object getlist(){
        return userMapper.selectList();
    }

}
