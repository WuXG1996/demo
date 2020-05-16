package com.example.demo.config.cache;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    
    private static final String FOLDER_STR = "::";

    /**
     * 获取redis对象
     * @param key
     * @return
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取redis对象-带包名
     * @param folder
     * @param key
     * @return
     */
    public Object get(String folder, String key){
        return this.get(folder.concat(FOLDER_STR).concat(key));
    }

    /**
     * 获取redis对象
     * string-get
     * @param key
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> entityClass){
        Object object = redisTemplate.opsForValue().get(key);
        if(object instanceof Integer
                || object instanceof BigDecimal
                || object instanceof String
                || object instanceof Boolean){
            return (T) object;
        }
        JSONObject jsonObject = (JSONObject) object;
        if(jsonObject==null){
            return null;
        }
        return jsonObject.toJavaObject(entityClass);
    }

    /**
     * 获取redis对象-带包名
     * @param folder
     * @param key
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> T get(String folder, String key, Class<T> entityClass){
        return this.get(folder.concat(FOLDER_STR).concat(key), entityClass);
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
     * 获取列表-带包名
     * @param folder
     * @param key
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> List<T> list(String folder, String key, Class<T> entityClass){
        return this.list(folder.concat(FOLDER_STR).concat(key), entityClass);
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
     * 设置缓存-带过期时间
     * @param key
     * @param object
     * @param timeout
     * @param unit
     */
    public void set(String key, Object object, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key, object, timeout, unit);
    }

    /**
     * 设置缓存-带文件夹名称和过期时间
     * @param folder
     * @param key
     * @param object
     * @param timeout
     * @param unit
     */
    public void set(String folder, String key, Object object, long timeout, TimeUnit unit){
        this.set(folder.concat(FOLDER_STR).concat(key), object, timeout, unit);
    }

    /**
     * 批量添加元素到set
     * @param key
     * @param collection
     */
    public Long sAdd(String key, Collection<String> collection){
        return redisTemplate.opsForSet().add(key, collection.toArray(new String[collection.size()]));
    }

    /**
     * 带包名的批量添加元素到set
     * @param folder
     * @param key
     * @param collection
     * @return
     */
    public Long sAdd(String folder, String key, Collection<String> collection){
        return this.sAdd(folder.concat(FOLDER_STR).concat(key), collection);
    }

    /**
     * 哈希添加元素
     * @param key
     * @param hashKey
     * @param value
     */
    public void hPut(String key, String hashKey, Object value){
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 带文件夹哈希添加元素
     * @param folder
     * @param key
     * @param hashKey
     * @param value
     */
    public void hPut(String folder, String key, String hashKey, Object value){
        this.hPut(folder.concat(FOLDER_STR).concat(key), hashKey, value);
    }
    
    /**
     * 哈希批量添加元素
     * @param key
     * @param map
     */
    public void hPutAll(String key, Map<String, Object> map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 带文件夹哈希批量添加元素
     * @param folder
     * @param key
     * @param map
     */
    public void hPutAll(String folder, String key, Map<String, Object> map){
        this.hPutAll(folder.concat(FOLDER_STR).concat(key), map);
    }
}
