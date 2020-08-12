package com.luping.security.config.buildconfig;


import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityBuilder;

/**
 * description: Builder <br>
 * date: 2020/8/12 16:52 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Builder extends AbstractConfiguredSecurityBuilder<User,Builder> implements SecurityBuilder<User> {
    private String a;
    private String b;

    public Builder(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);
    }

    @Override
    protected User performBuild() throws Exception {
        User user = new User();
        user.setPassword(a);
        user.setUsername(b);
        return user;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

}
