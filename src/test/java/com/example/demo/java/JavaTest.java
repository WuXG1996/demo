package com.example.demo.java;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by void on 2018/11/26.
 */
public class JavaTest {

    @Test
    public void test1(){
        System.out.println(String.format("(apply_types=%s OR hope_jobs=%s", "aa", "bb"));
    }

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

}
