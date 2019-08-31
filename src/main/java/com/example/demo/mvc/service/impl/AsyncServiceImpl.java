package com.example.demo.mvc.service.impl;

import com.example.demo.mvc.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author void
 * @date 2019/6/5 12:44
 * @desc
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Async
    @Override
    public void test1() {
        long d= System.currentTimeMillis();

        Thread thread = Thread.currentThread();
        try {
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long d2 = System.currentTimeMillis();
        log.info("异步方法执行:{},{},{}", d, d2, d2-d);
    }
}
