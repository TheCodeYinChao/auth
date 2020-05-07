package com.luping.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description: AuthenticationEntryPointImpl <br>
 * date: 2020/4/29 19:09 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("error ");
        Map map = new HashMap();
        map.put("code","403");
        map.put("msg","未授权");

        response.getWriter().write(map.toString());
    }
}
