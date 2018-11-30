package com.example.demo.other;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.config.JmsConfirguration;

/**
 * 消息消费者使用 @JmsListener 注解监听消息。
 * @author void
 * 2018年4月30日 下午7:39:59
 */
//@Component
public class JmsReceiver {
	//@JmsListener(destination = JmsConfirguration.QUEUE_NAME)
	public void receiveByQueue(String message) {
		System.out.println("接收队列消息:" + message);
	}

	//@JmsListener(destination = JmsConfirguration.TOPIC_NAME)
	public void receiveByTopic(String message) {
		System.out.println("接收主题消息:" + message);
	}
}
