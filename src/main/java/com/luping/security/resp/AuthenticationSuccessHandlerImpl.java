package com.luping.security.resp;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: AuthenticationSuccessHandlerImpl <br>
 * date: 2020/4/29 18:05 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("成功啦");
        response.sendRedirect(request.getContextPath());
    }
}
