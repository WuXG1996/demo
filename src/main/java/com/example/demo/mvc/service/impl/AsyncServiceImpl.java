package com.example.demo.mvc.service.impl;

import com.example.demo.mvc.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author void
 * @date 2019/6/5 12:44
 * @desc 异步注解测试
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Async
    @Override
    public void testAsync() {
        long d= System.currentTimeMillis();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long d2 = System.currentTimeMillis();
        log.info("异步方法执行:{},{},{}", d, d2, d2-d);
    }

    @Async
    @Override
    public void testAsync2(Integer i) {
        System.out.println("i的值:"+i);
    }

    private void printTest(int i){

    }
}
