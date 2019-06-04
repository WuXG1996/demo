package com.example.demo.web.aop;

import java.lang.annotation.*;

/**
 * Created by void on 2019/6/4.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SysLog {

    String value() default "";
}
