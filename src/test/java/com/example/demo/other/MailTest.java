package com.example.demo.other;

import com.example.demo.mvc.service.AsyncService;
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
public class MailTest{
    @Autowired
    private JavaMailComponent javaMailComponent;
    @Autowired
    
    @Test
    public void test() {
    	//发送邮件给1150662756@qq.com
        this.javaMailComponent.sendMail("1150662756@qq.com");
    }
}