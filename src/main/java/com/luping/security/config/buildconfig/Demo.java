package com.luping.security.config.buildconfig;


/**
 *
 * 这个是关于 security 的构建的逻辑代码 的一个认知
 * 大神就是大神
 * description: Demo <br>
 * date: 2020/8/12 17:01 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Builder bUilde = new Builder(new ObjectPostProcessorExt());
        CumstConfigurer cumstOME = new CumstConfigurer();

        bUilde.apply(cumstOME);
        User build = bUilde.build();
        System.out.println(build);
    }
}
