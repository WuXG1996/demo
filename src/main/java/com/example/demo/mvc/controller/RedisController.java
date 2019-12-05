package com.example.demo.mvc.controller;

import com.example.demo.mvc.pojo.IUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author void
 * @date 2019/11/28 17:56
 * @desc
 */
@Api(tags = "redis测试")
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发布订阅
     */
    @GetMapping("/pubSub")
    public void pubSub(String message){
        String channel = "channel-void";
        redisTemplate.convertAndSend(channel, message);
    }

    /**
     * 发布订阅
     */
    @GetMapping("/pubObject")
    public void pubObject(){
        String channel = "channel-void";
        IUser user = new IUser();
        user.setId(1);
        user.setAddress("深圳");
        user.setBirthday(new Date());
        user.setPhones(new ArrayList<String>(){{
            this.add("11111111111");
            this.add("22222222222");
        }});
        user.setUsername("void");
        redisTemplate.convertAndSend(channel, user);
    }
}
