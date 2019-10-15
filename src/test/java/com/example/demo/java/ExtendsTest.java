package com.example.demo.java;

import com.example.demo.other.test.Father;
import com.example.demo.other.test.Son;
import org.junit.Test;

/**
 * @author void
 * @date 2019/10/15 15:19
 * @desc
 */
public class ExtendsTest {

    @Test
    public void test1(){
        Father father = new Father();
        father.service();
    }

    /**
     * 判断调用哪个doGet取决于当前实例是哪个实例，查找方法有优先级
     */
    @Test
    public void test2(){
        Son son = new Son();
        son.service();
    }

    @Test
    public void test3(){
        Father father = new Son();
        father.service();
    }
}
