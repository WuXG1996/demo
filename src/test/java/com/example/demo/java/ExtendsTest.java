package com.example.demo.java;

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

    public class Father {

        public void service(){
            System.out.println("Father.service");
            doGet();
        }

        public void doGet(){
            System.out.println("Father.doGet");
        }
    }

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
}
