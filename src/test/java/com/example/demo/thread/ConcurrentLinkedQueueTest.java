package com.example.demo.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by void on 2020/1/12.
 * ConcurrentLinkedQueue  FIFO  每次poll方法取头部对象，需要手动判断null，not null表示获取到了，没取到就是null
 */
public class ConcurrentLinkedQueueTest {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for(int i=0;i<1000;i++){
            tickets.add("票 编号:"+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(true){
                    String s = tickets.poll();
                    if(s==null){
                        break;
                    }else{
                        System.out.println("销售了："+s);
                    }
                }
            }).start();
        }
    }
}
