package com.luping.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * description: SpringConfig <br>
 * date: 2020/4/29 11:06 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
@Configuration
public class SpringConfig {
    @Bean("messageSource")
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource(){
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new  ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.addBasenames("classpath:messages_zh_CN.properties");
        return reloadableResourceBundleMessageSource;
    }
}
