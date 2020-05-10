package com.example.demo.other.proxy;

public class HelloClass implements HelloInterface {
    
    @Override
    public void sayHello() {
        System.out.println("HelloClass.sayHello");
    }
}
