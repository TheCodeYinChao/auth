package com.luping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan("com.luping.dao")
public class LupingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LupingApplication.class, args);
    }

}
