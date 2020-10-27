package com.example.demo.mvc.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author void
 * @date 2019/6/5 12:50
 * @desc
 */
public interface TestService {

    void test2();

    /**
     * 阻塞中断测试
     */
    void test3();

    /**
     * 是否正常
     */
    void test4();

    /**
     * 事务测试
     */
    void transactionalTest();
}
