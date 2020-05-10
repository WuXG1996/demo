package com.example.demo.other.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 参考 https://www.jianshu.com/p/9bcac608c714
 */
public class TestProxy {

    /**
     * 静态代理，手动实现一个增强效果
     */
    @Test
    public void test1(){
        HelloClassProxy helloClassProxy = new HelloClassProxy();
        helloClassProxy.sayHello();
    }

    /**
     * 动态代理
     */
    @Test
    public void test2(){
        HelloInterface hello = new HelloClass();
        InvocationHandler handler = new ProxyHandler(hello);
        HelloInterface proxy = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);
        proxy.sayHello();
    }
}
