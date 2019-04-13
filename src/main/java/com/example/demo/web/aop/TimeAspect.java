package com.example.demo.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * aspect在interceptor执行了preHandler方法后执行
 * @author void
 *
 */
@Aspect
@Component
public class TimeAspect {
	@Around("execution(* com.example.demo.controller.FastJsonController..*(..))")
    public Object method(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("=====Aspect处理=======");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("参数为:" + arg);
        }
        long start = System.currentTimeMillis();
        Object object = pjp.proceed();
        System.out.println("Aspect 耗时:" + (System.currentTimeMillis() - start));
        return object;
    }
}
