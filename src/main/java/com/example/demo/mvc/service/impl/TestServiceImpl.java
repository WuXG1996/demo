package com.example.demo.mvc.service.impl;

import com.example.demo.mvc.service.AsyncService;
import com.example.demo.mvc.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author void
 * @date 2019/6/5 12:50
 * @desc
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService{

    @Autowired
    private AsyncService asyncService;

    @Override
    public void test2() {
        long d1 = System.currentTimeMillis();

        asyncService.test1();

        long d2 = System.currentTimeMillis();
        log.info("----------中间层执行耗时:{},{},{}", d1, d2, d2-d1);
    }

    @Override
    public void test3() {
        log.info("==========TestServiceImpl.test3主线程准备进入休眠===========");
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("==========TestServiceImpl.test3主线程准备进入休眠===========");
    }

    @Override
    public void test4() {
        log.info("===============TestServiceImpl.test4=============");
    }


}
