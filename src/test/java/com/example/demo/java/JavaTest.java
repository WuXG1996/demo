package com.example.demo.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by void on 2018/11/26.
 */
public class JavaTest {

    /**
     * 字符串连接
     */
    @Test
    public void test1(){
        System.out.println(String.format("(apply_types=%s OR hope_jobs=%s)", "aa", "bb"));
    }

    /**
     * debug hashMap的方法
     */
    @Test
    public void test2(){
        Map<String, Object> map = new HashMap<>(5);
        map.put(null, "aa");
        map.put("22", null);
        map.put("33", "cc");
        map.put("44", "dd");
        map.put("55", "ee");
        map.put("66", "ff");
        map.put("77", "gg");
        map.put("88", "hh");
        map.put("99", "ii");
        map.put("111", "jj");
    }

    /**
     * list可以直接用equals对比数据是否一致
     */
    @Test
    public void test3(){
        List<Integer> list = new ArrayList<>();
        list.add(new Integer(11));
        if(list.equals(new ArrayList<Integer>(){{
            this.add(new Integer(11));
        }})){
            System.out.println(11111);
        }
    }

    /**
     * 执行顺序
     */
    @Test
    public void test4(){
        System.out.println(1111);
        try{
            System.out.println(22222);
            return;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(333333);
        }finally {
            System.out.println(44444);
        }
        System.out.println(5555);
    }
}
