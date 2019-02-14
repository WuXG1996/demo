package com.example.demo.ali;

import com.aliyun.openservices.ons.api.*;

import java.util.Properties;

/**
 * Created by void on 2019/2/14.
 */
public class ProducerTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置groupId(统一了原先的消费者id和生产者id)
        properties.put(PropertyKeyConst.GROUP_ID, "GID_test");
        //阿里云密匙
        properties.put(PropertyKeyConst.AccessKey, "***");
        properties.put(PropertyKeyConst.SecretKey, "***");
        //TCP接入域名
        properties.put(PropertyKeyConst.NAMESRV_ADDR, "**");

        Producer producer = ONSFactory.createProducer(properties);
        producer.start();

//        while(true) {
            Message msg = new Message("topic_test", "TagB", "测试消息".getBytes());
            //可以塞入键值对但是所有键值对大小加起来不能超过32k
//            msg.putUserProperties("name", "wxg");
            // 设置代表消息的业务关键属性，请尽可能全局唯一，以方便您在无法正常收到消息情况下，可通过控制台查询消息并补发
            msg.setKey("ORDERID_100");
            SendResult sendResult = producer.send(msg);
            System.out.println("Send Message success. Message ID is: " + sendResult.getMessageId());
//        }
        producer.shutdown();
    }
}
