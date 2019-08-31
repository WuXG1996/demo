package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by void on 2019/4/9.
 * 使用指定的properties文件获取配置信息
 */
@Component
@PropertySource(value = "classpath:book.properties",encoding = "GBK")
@ConfigurationProperties(prefix = "book")
public class BookConfig {

    String name;
    String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
