package com.example.demo.mvc.controller;

import com.example.demo.mvc.service.AsyncService;
import com.example.demo.mvc.service.TestService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author void
 * @date 2019/6/5 12:43
 * @desc 测试异步线程执行
 */
@Api(tags = {"异步测试"})
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
        asyncService.testAsync();
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

    @GetMapping("/test3")
    public void test3(){
        for(int i=0;i<10;i++){
            asyncService.testAsync2(i);
        }
    }
}
