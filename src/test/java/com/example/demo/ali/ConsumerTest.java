package com.example.demo.ali;

import com.aliyun.openservices.ons.api.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by void on 2019/2/14.
 */
public class ConsumerTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置groupId(统一了原先的消费者id和生产者id)
        properties.put(PropertyKeyConst.GROUP_ID, "GID_test");
        //阿里云密匙
        properties.put(PropertyKeyConst.AccessKey, "***");
        properties.put(PropertyKeyConst.SecretKey, "***");
        //TCP接入域名
        properties.put(PropertyKeyConst.NAMESRV_ADDR, "***");

        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("topic_test", "TagB", new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext consumeContext) {
                try {
                    System.out.println(new String(message.getBody(), "utf-8"));
//                    System.out.println(message.getUserProperties("name"));
                    System.out.println("重复消费次数:"+message.getReconsumeTimes());
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
