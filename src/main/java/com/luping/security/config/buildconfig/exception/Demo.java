package com.luping.security.config.buildconfig.exception;

import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;

import javax.servlet.ServletException;

/**
 * description: Demo <br>
 * date: 2020/8/13 11:02 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Demo {
    private static ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    public static void main(String[] args) {
        try {
            new Demo().a();
        } catch (Exception e) {
            Throwable[] throwables = throwableAnalyzer.determineCauseChain(e);
            e.printStackTrace();
        }

    }

    public void a(){
        try {
            b();
        } catch (Exception e) {
            throw e;
        }
    }

    public  void b(){
        throw new ArithmeticException("bbb");
    }



    private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer {
        /**
         * @see org.springframework.security.web.util.ThrowableAnalyzer#initExtractorMap()
         */
        protected void initExtractorMap() {
            super.initExtractorMap();

            registerExtractor(ServletException.class, new ThrowableCauseExtractor() {
                public Throwable extractCause(Throwable throwable) {
                    ThrowableAnalyzer.verifyThrowableHierarchy(throwable,
                            ServletException.class);
                    return ((ServletException) throwable).getRootCause();
                }
            });
        }

    }
}
