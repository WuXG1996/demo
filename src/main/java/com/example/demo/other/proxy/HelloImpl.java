package com.example.demo.other.proxy;

public class HelloImpl implements Hello {
    @Override
    public void morning(String name) {
        System.out.println("Good morning, "+name);
    }
}
