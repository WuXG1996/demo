package com.example.demo.config.cache;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * RedisTemplate默认是JDK的序列化策略,更改成与StringRedisTemplate一样的序列化方式
     * **存取对象数据建议使用JSON.toJsonString存入redis,获取数据也是用json转化这样的兼容性是最好的，不会出现因为对象字段删除，缓存中存在被删除的字段信息，直接强制类型转化出错
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // 建议使用这种方式，小范围指定白名单，据说fastjson有一个bug，这样安全一些
        ParserConfig.getGlobalInstance().addAccept("com.example.");

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(fastJsonRedisSerializer);
        return template;
    }
    /*备注
    * template的value序列化使用fastjson,对象可以直接用redisTemplate存入读取,无需Json.toJSONString,
    * 如果这样操作会导致序列化两次,第一次吧java bean序列化为string
    * 第二次把string序列化转义字符,导致在redis GUI里查看的数据都带了转义的"",影响查看体验
    * 取出对象的时候强制类型转化为JSONObject再使用toJavaObject映射成具体对象
    * */

    /**
     * 定义一个发布订阅的消费者,交给spring管理是为了在里面注入redisTemplate,利用redisTemplate设置的序列化方案来解码
     * @return
     */
    @Bean
    public RedisMessageListener consumerRedis() {
        return new RedisMessageListener();
    }

    /**
     * 绑定消息监听者和接收监听的方法
     */
    @Bean
    public MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new RedisSubscriber());
    }

    /**
     * redis发布订阅监听容器
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(consumerRedis(), new ChannelTopic("channel-void"));
        container.addMessageListener(listenerAdapter(), new PatternTopic("test"));
        return container;
    }
}
