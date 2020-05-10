package com.example.demo.other.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class Test2 {

    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Test2.invoke: "+method);
                if(method.getName().equals("morning")){
                    System.out.println("Good morning, "+args[0]);
                }
                return null;
            }
        };
        // 1.传入ClassLoader 2.传入要实现的接口 3.传入处理调用方法的InvocationHandler
        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, handler);
        hello.morning("aaa");
    }
}
