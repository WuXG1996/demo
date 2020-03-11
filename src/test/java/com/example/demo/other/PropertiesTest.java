package com.example.demo.other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by void on 2019/4/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PropertiesTest {

    @Autowired
    private Environment environment;

    @Test
    public void test(){
        System.out.println(environment.getProperty("spring.cache.type"));
    }
}
