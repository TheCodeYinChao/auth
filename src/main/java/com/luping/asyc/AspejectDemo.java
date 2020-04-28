package com.luping.asyc;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * description: AspejectDemo <br>
 * date: 2020/3/20 18:41 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
@EnableAspectJAutoProxy
@Aspect  //这个注解是spring aop 里面的注解 他是aspect的语法  spring 默认实现一套自己的aop语法 但这个更让人接受 从而spring 去 支持他
public class AspejectDemo {

}
