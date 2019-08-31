package com.example.demo.mvc.pojo.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by void on 2019/1/14.
 */
public class Question {

    @Id
    private String questionId;
    private String content;

    private Double test;

    @DBRef
    private List<Answer> answers;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Double getTest() {
        return test;
    }

    public void setTest(Double test) {
        this.test = test;
    }
}
