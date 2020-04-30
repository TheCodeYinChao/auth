package com.luping.controller;

/**
 * description: LoginController <br>
 * date: 2020/4/30 14:23 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
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

@RestController
public class LoginController {

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
}
