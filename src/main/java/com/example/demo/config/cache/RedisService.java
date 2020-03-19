package com.example.demo.config.cache;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author void
 * @date 2020/3/19 18:03
 * @desc
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        return ((JSONObject) redisTemplate.opsForValue().get(key)).toJavaObject(entityClass);
    }
}
