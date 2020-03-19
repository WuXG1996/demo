package com.example.demo.config.cache;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author void
 * @date 2020/3/19 18:03
 * @desc
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取redis对象
     * string-get
     * @param key
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> entityClass){
        return ((JSONObject) redisTemplate.opsForValue().get(key)).toJavaObject(entityClass);
    }

    /**
     * 获取列表
     * string-get
     * @param key
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> List<T> list(String key, Class<T> entityClass){
        return ((JSONArray)redisTemplate.opsForValue().get(key)).toJavaList(entityClass);
    }

    /**
     * 设置缓存
     * @param key
     * @param object
     */
    public void set(String key, Object object){
        redisTemplate.opsForValue().set(key, object);
    }

    /**
     * 设置缓存
     * @param key
     * @param object
     * @param timeout
     * @param unit
     */
    public void set(String key, Object object, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key, object, timeout, unit);
    }
}
