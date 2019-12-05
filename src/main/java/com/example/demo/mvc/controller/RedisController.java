package com.example.demo.mvc.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
