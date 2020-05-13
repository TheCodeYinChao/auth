package com.luping.security.config;

import com.luping.security.CustomUserService;
import com.luping.security.JwtAuthenticationProvider;
import com.luping.security.exception.AuthenticationEntryPointImpl;
import com.luping.security.filter.JwtAuthenticationFilter;
import com.luping.security.filter.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

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
 * Spring Security中，安全构建器HttpSecurity和WebSecurity的区别是 :
 *
 * WebSecurity不仅通过HttpSecurity定义某些请求的安全控制，也通过其他方式定义其他某些请求可以忽略安全控制;
 * HttpSecurity仅用于定义需要安全控制的请求(当然HttpSecurity也可以指定某些请求不需要安全控制);
 * 可以认为HttpSecurity是WebSecurity的一部分，WebSecurity是包含HttpSecurity的更大的一个概念;
 * 构建目标不同
 * WebSecurity构建目标是整个Spring Security安全过滤器FilterChainProxy,
 * 而HttpSecurity的构建目标仅仅是FilterChainProxy中的一个SecurityFilterChain。
 *
 *
 * @see org.springframework.security.web.FilterChainProxy
 * @see org.springframework.security.web.SecurityFilterChain
 *
 *
 * 除了上面的两个还有一个WebSecurity 中的 FilterSecurityInterceptor 这个是鉴权的核心filter
 * @see org.springframework.security.web.access.intercept.FilterSecurityInterceptor
 * 集成
 * @see AccessDecisionManager  提供三种规则 1 一个通过就行 2 全部通过 3 少数服从多数
 *
 * @see org.springframework.security.access.AccessDecisionVoter 投票的一些机制
 *
 * 这里面有涉及到一个超级管理员自定义投票机制相关
 * <a href='https://www.cnblogs.com/woshimrf/p/spring-security-token-server.html'/>
 *
 *
 *
 *
 * 授权决策器
 * AccessDecisionManager ----实现多种 一个通过即通过 大多数通过即通过这个  数量指的是什么呢？？
 * 其实是 AccessDecisionVoter 的数量  这里面 AccessDecisionVoter会有多组  通过AccessDecisionVoter的决策满足条件的数量来对应 AccessDecisionManager的校验条件
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
        // 开启登录认证流程过滤器 扩展
        http.addFilterBefore(new JwtLoginFilter(authenticationManager()), JwtLoginFilter.class);
        // 访问控制时登录状态检查过滤器 token解析
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
