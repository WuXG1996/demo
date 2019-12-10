package com.example.demo.java;

import com.example.demo.utils.IdGenerate;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author void
 * @date 2019/12/9 17:14
 * @desc
 */
public class SourceTest {

    @Test
    public void test1(){
        //内部包装了一个HashMap
        Set<String> set = new HashSet<>();
        for(int i=0;i<10;i++){
            set.add(IdGenerate.getRandomStringByLength(5));
        }
        System.out.println(111);
    }
}
