package com.luping.security.config;

import com.luping.security.CustomUserService;
import com.luping.security.JwtAuthenticationProvider;
import com.luping.security.exception.AuthenticationEntryPointImpl;
import com.luping.security.filter.JwtAuthenticationFilter;
import com.luping.security.filter.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * description: WebConfig <br>
 * date: 2020/4/28 18:22 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 *  1这里的核心对象及其相关作用
 *    a、 WebSecurityConfigurerAdapter  主要配置的构建 其实 类似于 构建安全核心的地基
 *    b、 AuthenticationManagerBuilder  这里顾名思义 是认证管理者的构建器 需要加入 用户信息的获取 用户认证规则等
 *    c、 WebSecurity   这里是对请求资源的验证
 *    d、 HttpSecurity  这里定制http的访问规则
 *
 *
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserService customUserService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /**自定义 认证组件*/
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider();
        jwtAuthenticationProvider.setUserDetailsService(customUserService);
        auth.authenticationProvider(jwtAuthenticationProvider);
        super.configure(auth);

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
//                .withObjectPostProcessor(
//                new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        //o.setAuthenticationManager();
////                        o.setSecurityMetadataSource(invocationSecurityMetadataSourceImpl);
//                        o.setAccessDecisionManager(accessDecisionManagerImpl);
//                        return o;
//                    }
//                })
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录URL
                .antMatchers("/user/login").permitAll()
                // swagger
//                .antMatchers("/swagger**/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/**").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();
        // 退出登录处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        // 开启登录认证流程过滤器
//        http.addFilterBefore(new JwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        // 访问控制时登录状态检查过滤器
//        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new JwtLoginFilter(authenticationManager()), JwtLoginFilter.class);

        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl());

    }
    /**
     * 密码验证
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.equals(s);
            }
        };
    }
}
