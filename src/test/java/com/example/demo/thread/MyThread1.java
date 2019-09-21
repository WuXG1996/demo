package com.example.demo.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author void
 * @date 2019/9/21 11:28
 * @desc
 */
public class MyThread1 implements Runnable{
    private List<Object> list;
    private CountDownLatch countDownLatch;

    public MyThread1(List<Object> list, CountDownLatch countDownLatch) {
        this.list = list;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            list.add(new Object());
        }

        //完成一个子线程
        countDownLatch.countDown();
    }
}
