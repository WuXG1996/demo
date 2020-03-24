package com.example.demo.config.cache;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class RedisSubscriber {

    /**
     * 默认处理消息的方法
     * MessageListenerAdapter可以传入一个实际实现的对象,源码中指定了handleMessage为原始监听的方法
     * @param message
     */
    public void handleMessage(String message) {
        System.out.println("RedisSubscriber.handleMessage:"+message);
    }
}