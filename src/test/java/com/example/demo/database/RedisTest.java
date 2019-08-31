package com.example.demo.database;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author void
 * @date 2019/8/31 15:51
 * @desc
 */
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    private static String key1 = "key1";
    private static String key2 = "key2";

    @Test
    public void test1(){
        System.out.println(redisTemplate.hasKey(key1));

//        redisTemplate.delete(key1);

        //序列化给定 key ，并返回被序列化的值
        System.out.println(redisTemplate.dump(key1));

        //多久后过期
        redisTemplate.expire(key1, 7L, TimeUnit.MINUTES);

        //到什么时间点过期
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 2);
        redisTemplate.expireAt(key1, c.getTime());

        //默认秒形式
        System.out.println(redisTemplate.getExpire(key1));
        System.out.println(redisTemplate.getExpire(key1, TimeUnit.MINUTES));
        //不足一小时，就向下取整
        System.out.println(redisTemplate.getExpire(key1, TimeUnit.HOURS));
    }

    @Test
    public void test2(){
        //正则匹配获取缓存集合
//        Set<String> set = redisTemplate.keys("*key*");

        //检查这个集合里的key有多少存在
        List<String> list = ImmutableList.of(key1, key2);
        System.out.println(redisTemplate.countExistingKeys(list));

        //随机返回
//        String obj = (String) redisTemplate.randomKey();

        //强制改名key10存在也被替换
//        redisTemplate.rename(key1, "key10");

        //当key10不存在时替换改名
//        redisTemplate.renameIfAbsent(key1, "key10");

        //获取key的类型
        System.out.println(redisTemplate.type(key1));
    }

    //***********普通string**********

    @Test
    public void test10(){
//        redisTemplate.opsForValue().set(key1, "111");
//        redisTemplate.opsForValue().set(key2, "222");

        redisTemplate.opsForValue().get(key1);
    }

    @Test
    public void test11(){
//        redisTemplate.opsForValue().set(key1, "111", 7L, TimeUnit.MINUTES);

        Map<String, Object> map = new HashMap<>();
        map.put(key1, "333");
        map.put(key2, "444");
        redisTemplate.opsForValue().multiSet(map);

        Set<String> set = redisTemplate.keys("*key*");
        //批量get按顺序获取值
        List<String> list = redisTemplate.opsForValue().multiGet(set);
    }

    @Test
    public void test12(){
        redisTemplate.opsForValue().increment("key3");
        //一次累加多少
        redisTemplate.opsForValue().increment("key3", 20);
    }

    //*********************hash******************
    @Test
    public void test20(){

    }
}
