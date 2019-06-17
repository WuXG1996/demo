package com.example.demo.service.impl;

import com.example.demo.service.AsyncService;
import com.example.demo.service.TestService;
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
}
