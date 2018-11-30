package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by void on 2018/9/10.
 */
@Aspect
@Component
public class TestAnnotationAspect {

    private Logger logger = LoggerFactory.getLogger(TestAnnotationAspect.class);

    @Pointcut("@annotation(com.example.demo.aop.TestAnnotation)")
    private void pointCut(){
        System.out.println(11111);
    }

    @Around("pointCut() && @annotation(testAnnotation)")
    public Object around(ProceedingJoinPoint point, TestAnnotation testAnnotation) throws Throwable {
        long beginTime = System.currentTimeMillis();

        Object result = point.proceed();

        long time = System.currentTimeMillis()-beginTime;
        logger.info("注解切面执行耗时:{}", time);
        logger.info("方法传入的参数:id-{},msg-{}", testAnnotation.id(), testAnnotation.msg());


        return result;
    }


}
