package com.luping.controller;

import com.luping.asyc.AsycDemo;
import com.luping.security.jwt.JwtAuthenticatioToken;
import com.luping.security.jwt.SecurityUtils;
import com.luping.security.vo.LoginBean;
import com.luping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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


    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public Object login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();

        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);

        return token;
    }

    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/findAll")
    public Object findAll() {
        return "the findAll service is called success.";
    }
}
