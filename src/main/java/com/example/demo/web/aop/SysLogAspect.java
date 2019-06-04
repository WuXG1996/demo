package com.example.demo.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by void on 2019/6/4.
 */
@Component
@Aspect
@Slf4j
public class SysLogAspect {

    @Pointcut("@annotation(com.example.demo.web.aop.SysLog)")
    public void logPointCut(){
        System.out.println(22222222);
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //执行方法
        Object result = point.proceed();

        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = method.getAnnotation(SysLog.class);

        log.info("----方法名:{}-----", signature.getDeclaringTypeName()+"."+method.getName());
        log.info("----注解参数:{}-----", sysLog.value());

        return result;
    }
}
