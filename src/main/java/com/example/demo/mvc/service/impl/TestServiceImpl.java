package com.example.demo.mvc.service.impl;

import com.example.demo.dao.mysql.mapper.UserMapper;
import com.example.demo.dao.mysql.model.User;
import com.example.demo.mvc.service.AsyncService;
import com.example.demo.mvc.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author void
 * @date 2019/6/5 12:50
 * @desc
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService{

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void test2() {
        long d1 = System.currentTimeMillis();

        asyncService.testAsync();

        long d2 = System.currentTimeMillis();
        log.info("----------中间层执行耗时:{},{},{}", d1, d2, d2-d1);
    }

    @Override
    public void test3() {
        log.info("==========TestServiceImpl.test3主线程准备进入休眠===========");
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("==========TestServiceImpl.test3主线程准备进入休眠===========");
    }

    @Override
    public void test4() {
        log.info("===============TestServiceImpl.test4=============");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionalTest() {
        User user = new User();
        user.setUsername("张三");
        userMapper.insertSelective(user);

        mongoTemplate.insert(user);

        int a = 0;
        System.out.println(1/0);
    }
}
