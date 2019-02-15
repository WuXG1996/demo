package com.example.demo.ali;

import com.aliyun.openservices.ons.api.*;
import com.example.demo.domain.constant.AliyunConstant;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by void on 2019/2/14.
 */
public class Consumer2Test {

    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置groupId(统一了原先的消费者id和生产者id)
        properties.put(PropertyKeyConst.GROUP_ID, AliyunConstant.groupId2);
        //阿里云密匙
        properties.put(PropertyKeyConst.AccessKey, AliyunConstant.accessKey);
        properties.put(PropertyKeyConst.SecretKey, AliyunConstant.secretKey);
        //TCP接入域名
        properties.put(PropertyKeyConst.NAMESRV_ADDR, AliyunConstant.namesRvAddr);
        //默认为CLUSTERING集群订阅方式,可以修改为广播订阅
        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);

        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(AliyunConstant.topicId, "TagD", new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext consumeContext) {
                try {
//                    System.out.println(System.currentTimeMillis());
                    System.out.println(new String(message.getBody(), "utf-8"));
//                    System.out.println(message.getUserProperties("name"));
//                    System.out.println("重复消费次数:"+message.getReconsumeTimes());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Action.CommitMessage;
            }
        });
        consumer.start();
        System.out.println("Consumer start");
    }
}
