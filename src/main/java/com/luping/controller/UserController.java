package com.luping.controller;

import com.luping.asyc.AsycDemo;
import com.luping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AsycDemo asycDemo;

    @GetMapping("/getList")
    public Object getList(){
        return asycDemo.asyncMethodWithReturnType();
    }
}
