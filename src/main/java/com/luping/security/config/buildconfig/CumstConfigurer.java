package com.luping.security.config.buildconfig;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;


/**
 * description: CumstConfigurer <br>
 * date: 2020/8/12 16:52 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class CumstConfigurer extends SecurityConfigurerAdapter<User,Builder> {

    @Override
    public void init(Builder builder) throws Exception {
        builder.setA("一一2");
        System.out.println("init");
        super.init(builder);
    }

    @Override
    public void configure(Builder builder) throws Exception {
        builder.setB(",,,,,,add");
        System.out.println("configure");
        super.configure(builder);
    }
}
