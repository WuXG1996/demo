package com.example.demo.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * @author void
 * @date 2019/9/21 11:22
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            test();
        }
    }

    public static void test(){
//        List<Object> list = new ArrayList<>();
        List<Object> list = new Vector<>();

        int threadCount = 1000;

        // 用来让主线程等待threadCount个子线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){
            Thread thread = new Thread(new MyThread1(list, countDownLatch));
            thread.start();
        }

        try {
            // 主线程等待所有子线程执行完成，再向下执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // List的size
        System.out.println(list.size());
    }
}
