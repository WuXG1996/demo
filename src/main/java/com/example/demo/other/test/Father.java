package com.example.demo.other.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author void
 * @date 2019/10/15 15:16
 * @desc
 */
public class Father {

    public void service(){
        System.out.println("Father.service");
        doGet();
    }

    public void doGet(){
        System.out.println("Father.doGet");
    }
}
