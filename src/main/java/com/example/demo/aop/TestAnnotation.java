package com.example.demo.aop;

import java.lang.annotation.*;

/**
 * Created by void on 2018/9/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Target(ElementType.METHOD)
public @interface TestAnnotation {
    public int id() default -1;
    public String msg() default "Hi";
}
