package com.example.demo.mvc.pojo.mongodb;

import org.springframework.data.annotation.Id;

/**
 * Created by void on 2019/1/14.
 */
public class Answer {

    @Id
    private String answerId;

    private String content;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer() {
    }

    public Answer(String content) {
        this.content = content;
    }

    public Answer(String answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }
}
