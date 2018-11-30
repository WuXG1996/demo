package com.example.demo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Jms:Java Message Service,Queue和Topic都要用java.jms下的
 * ActiveMQ配置类
 * @author void
 * 2018年4月30日 下午7:25:35
 */
//@Configuration
public class JmsConfirguration {
	public static final String QUEUE_NAME = "activemq_queue";
	public static final String TOPIC_NAME = "activemq_topic";

	@Bean
	public Queue queue() {
		return new ActiveMQQueue(QUEUE_NAME);
	}

	@Bean
	public Topic topic() {
		return new ActiveMQTopic(TOPIC_NAME);
	}
}