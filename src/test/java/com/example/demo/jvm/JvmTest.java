package com.example.demo.jvm;

import com.example.demo.mvc.pojo.mongodb.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author void
 * @date 2020/6/14 21:32
 * @desc 使用MemoryAnalyzer 分析
 */
public class JvmTest {

    /**
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        while(true){
            list.add(new User());
        }
    }
}
