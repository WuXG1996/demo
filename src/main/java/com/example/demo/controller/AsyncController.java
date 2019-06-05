package com.example.demo.controller;

import com.example.demo.service.AsyncService;
import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author void
 * @date 2019/6/5 12:43
 * @desc
 */
@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private TestService testService;

    @GetMapping("/test1")
    public String test1(){
        long d1 = System.currentTimeMillis();
        asyncService.test1();
        long d2 = System.currentTimeMillis();
        log.info("外层执行耗时:d1:{},d2:{}", d1, d2);
        return "123";
    }

    @GetMapping("/test2")
    public String test2(){
        long d1 = System.currentTimeMillis();
        testService.test2();
        long d2 = System.currentTimeMillis();
        log.info("外层执行耗时:d1:{},d2:{},{}", d1, d2, d2-d1);
        return "222";
    }
}
