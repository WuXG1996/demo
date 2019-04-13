package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by void on 2019/4/9.
 */
@Component
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:aa.yml")
@ConfigurationProperties(prefix = "person")
public class TestConfig {

    private String name;
    private Integer age;
    private Date birth;
    private Map<String,String> maps;
    private List<String> like;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public List<String> getLike() {
        return like;
    }

    public void setLike(List<String> like) {
        this.like = like;
    }

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
