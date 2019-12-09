package com.example.demo.java;

import org.junit.Test;

/**
 * @author void
 * @date 2019/12/9 16:14
 * @desc
 */
public class StringTest {

    @Test
    public void test1(){
        String str = new String("void");
        System.out.println(str.hashCode());
    }

    @Test
    public void test2(){
        String str1 = null;
        String str2 = "aaa";
        System.out.println(str1+str2);
        //java会自动创建并调用StringBuilder.append拼接字符串(jdk1.5之前使用StringBuffer)
    }

    @Test
    public void test3(){
        String str1 = new String("void");
        String str2 = "void";
        String str3 = "void";
        System.out.println(str1==str2);
        System.out.println(str2==str3);
        str1 = str1.intern();
        //显式加入常量池并且改变str1的指向
        System.out.println(str1==str2);
        System.out.println(str1==str3);
    }
}
