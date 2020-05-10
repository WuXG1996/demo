package com.example.demo.other.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理处理，为原先的方法做效果增强
 */
public class ProxyHandler implements InvocationHandler {
    
    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ProxyHandler.invoke Before");
        method.invoke(object, args);
        System.out.println("ProxyHandler.invoke After");
        return null;
    }
}
