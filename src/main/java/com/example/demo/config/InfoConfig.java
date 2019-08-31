package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by void on 2019/4/9.
 * 测试使用指定的yml文件获取配置
 */
@Data
@Component
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:info.yml")
@ConfigurationProperties(prefix = "person")
public class InfoConfig {

    private String name;
    private Integer age;
    private Date birth;
    private Map<String,String> maps;
    private List<String> like;

    @Override
    public String toString() {
        return "TestConfig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                ", maps=" + maps +
                ", like=" + like +
                '}';
    }
}
