package com.example.demo.ali;

import com.aliyun.openservices.ons.api.*;
import com.example.demo.domain.constant.AliyunConstant;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by void on 2019/2/14.
 */
public class ProducerTest {

    private Properties properties;

    private static Logger logger = LoggerFactory.getLogger(ProducerTest.class);

    @Before
    public void init(){
        properties = new Properties();
        //设置groupId(统一了原先的消费者id和生产者id)
        properties.put(PropertyKeyConst.GROUP_ID, AliyunConstant.groupId);
        //阿里云密匙
        properties.put(PropertyKeyConst.AccessKey, AliyunConstant.accessKey);
        properties.put(PropertyKeyConst.SecretKey, AliyunConstant.secretKey);
        //TCP接入域名
        properties.put(PropertyKeyConst.NAMESRV_ADDR, AliyunConstant.namesRvAddr);
    }

    @Test
    public void test1(){
        Properties properties = new Properties();
        //设置groupId(统一了原先的消费者id和生产者id)
        properties.put(PropertyKeyConst.GROUP_ID, AliyunConstant.groupId);
        //阿里云密匙
        properties.put(PropertyKeyConst.AccessKey, AliyunConstant.accessKey);
        properties.put(PropertyKeyConst.SecretKey, AliyunConstant.secretKey);
        //TCP接入域名
        properties.put(PropertyKeyConst.NAMESRV_ADDR, AliyunConstant.namesRvAddr);

        Producer producer = ONSFactory.createProducer(properties);
        producer.start();

//        while(true) {
            Message msg = new Message(AliyunConstant.topicId, "TagB", "测试消息".getBytes());
            //可以塞入键值对但是所有键值对大小加起来不能超过32k
//            msg.putUserProperties("name", "wxg");
            // 设置代表消息的业务关键属性，请尽可能全局唯一，以方便您在无法正常收到消息情况下，可通过控制台查询消息并补发
            msg.setKey("ORDERID_100");
            SendResult sendResult = producer.send(msg);
            System.out.println("Send Message success. Message ID is: " + sendResult.getMessageId());
//        }
        producer.shutdown();
    }

    @Test
    public void test2(){
        Producer producer = ONSFactory.createProducer(properties);
        producer.start();
        Message msg = new Message(AliyunConstant.topicId, "TagC", "spring春天".getBytes());
        msg.setKey("void123");
        //异步发送消息
        producer.sendAsync(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                //消费发送成功
                logger.info("消费发送成功了,topicId:{},messageId:{}", sendResult.getTopic(), sendResult.getMessageId());
            }

            @Override
            public void onException(OnExceptionContext onExceptionContext) {
                // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
                System.out.println("send message failed. topic=" + onExceptionContext.getTopic() + ", msgId=" + onExceptionContext.getMessageId());
            }
        });

        // 在 callback 返回之前即可取得 msgId。
        System.out.println("send message async. topic=" + msg.getTopic() + ", msgId=" + msg.getMsgID());
    }

    /**
     * 多线程发送
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置groupId(统一了原先的消费者id和生产者id)
        properties.put(PropertyKeyConst.GROUP_ID, AliyunConstant.groupId);
        //阿里云密匙
        properties.put(PropertyKeyConst.AccessKey, AliyunConstant.accessKey);
        properties.put(PropertyKeyConst.SecretKey, AliyunConstant.secretKey);
        //TCP接入域名
        properties.put(PropertyKeyConst.NAMESRV_ADDR, AliyunConstant.namesRvAddr);
        final Producer producer = ONSFactory.createProducer(properties);
        producer.start();

        //创建的 Producer 和 Consumer 对象为线程安全的，可以在多线程间进行共享，避免每个线程创建一个实例。

        //在 thread 和 anotherThread 中共享 producer 对象，并发地发送消息至 MQ。
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = new Message(AliyunConstant.topicId, "TagD", "hello mq,this is tagD".getBytes());
                    msg.setKey("aaa");
                    SendResult sendResult = producer.send(msg);
                    if(sendResult!=null){
                        logger.info("线程thread发送成功,topic:{},msgId:{}", sendResult.getTopic(), sendResult.getMessageId());
                    }
                }catch (Exception e){
                    //发送失败
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread anotherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = new Message(AliyunConstant.topicId, "TagA", "hello mq,this is tagA".getBytes());
                    msg.setKey("aaa");
                    SendResult sendResult = producer.send(msg);
                    if(sendResult!=null){
                        logger.info("线程anotherThread发送成功,topic:{},msgId:{}", sendResult.getTopic(), sendResult.getMessageId());
                    }
                }catch (Exception e){
                    //发送失败
                    e.printStackTrace();
                }
            }
        });
        anotherThread.start();
    }

    /**
     * 延时投递
     */
    @Test
    public void test3(){
        Producer producer = ONSFactory.createProducer(properties);
        producer.start();

        long timeStamp = System.currentTimeMillis()+10000;//10秒后投递
        System.out.println(timeStamp);
        Message msg = new Message(AliyunConstant.topicId, "TagA", "hello mq,this is tagA".getBytes());
        msg.setKey("gg");
        msg.setStartDeliverTime(timeStamp);

        SendResult sendResult = producer.send(msg);
        System.out.println("Message Id:" + sendResult.getMessageId());
    }
}
