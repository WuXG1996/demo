package com.example.demo.other.proxy;

/**
 * 静态代理
 */
public class Test1 {

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        hello.morning("wxg");
    }
}
