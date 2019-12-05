package com.example.demo.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author void
 * @date 2019/12/5 9:43
 * @desc
 */
public class RedisMessageListener implements MessageListener{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        String msgBody = (String) redisTemplate.getValueSerializer().deserialize(body);
        System.out.println(msgBody);

        String msgChannel = (String) redisTemplate.getStringSerializer().deserialize(pattern);
        System.out.println(msgChannel);
    }
}
