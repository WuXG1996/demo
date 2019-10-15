package com.example.demo.other.test;

/**
 * @author void
 * @date 2019/10/15 15:17
 * @desc
 */
public class Son extends Father {

    @Override
    public void service(){
        System.out.println("Son.service");
        super.service();
    }

    @Override
    public void doGet() {
        System.out.println("Son.doGet");
    }
}
