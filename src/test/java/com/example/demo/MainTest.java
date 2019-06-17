package com.example.demo;

import com.example.demo.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.other.JavaMailComponent;

/**
 * 测试邮件发送
 * @author void
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MainTest{
    @Autowired
    private JavaMailComponent javaMailComponent;
    @Autowired
    private AsyncService asyncService;
    
    @Test
    public void test() {
    	//发送邮件给1150662756@qq.com
        this.javaMailComponent.sendMail("1150662756@qq.com");
    }

    @Test
    public void test1(){
        long d1 = System.currentTimeMillis();
        asyncService.test1();
        long d2 = System.currentTimeMillis();
        log.info("耗时:{}", d2-d1);
        System.out.println(1111);
    }
}