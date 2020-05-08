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

//    @PreAuthorize、@PostAuthorize、@PreFilter和@PostFilter (https://blog.csdn.net/weixin_39220472/article/details/80873268?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1)
    @PreAuthorize("hasAuthority('sys:user:view') and hasRole('ADMIN')")
    @GetMapping(value="/findAll")
    public Object findAll() {
        return "the findAll service is called success.";
    }
}
