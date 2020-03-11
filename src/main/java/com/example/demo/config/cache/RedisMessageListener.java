package com.example.demo.config.cache;

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
//        System.out.println("消息:"+message.toString());
        System.out.println(redisTemplate.getStringSerializer().deserialize(message.getBody()));

        //也可以通过message读取body的字节数组解析成字符串
//        String msgBody = (String) redisTemplate.getValueSerializer().deserialize(body);
//        System.out.println(msgBody);

        //直接读pattern和message.getChannel是一样的,在RedisMessageListenerContainer的里面实际执行发布订阅的类DispatchMessageListener实现了
        //MessageListener接口,但是byte[] pattern是空的,pattern为空时就会设置为message里的渠道字节数组
        String msgChannel = (String) redisTemplate.getStringSerializer().deserialize(pattern);
        System.out.println("订阅渠道:"+msgChannel);
    }
}
