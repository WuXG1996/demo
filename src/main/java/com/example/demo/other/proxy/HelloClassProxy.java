package com.example.demo.other.proxy;

public class HelloClassProxy implements HelloInterface {
    
    private HelloInterface helloInterface = new HelloClass();
    
    @Override
    public void sayHello() {
        System.out.println("HelloClassProxy.sayHello Before");
        helloInterface.sayHello();
        System.out.println("HelloClassProxy.sayHello After");
    }
}
