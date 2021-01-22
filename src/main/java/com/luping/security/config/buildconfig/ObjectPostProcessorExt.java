package com.luping.security.config.buildconfig;

import org.springframework.security.config.annotation.ObjectPostProcessor;

/**
 * dsc: ObjectPostProcessorExt
 * date: 2021/1/22 13:55
 * author: zyc
 */
public class ObjectPostProcessorExt implements ObjectPostProcessor {
    @Override
    public Object postProcess(Object object) {
        if (object instanceof User) {
            User user = (User) object;
            user.setUsername("我是对象后置处理器把他给改掉啦");
            object = user;
        }

        return object;
    }
}
