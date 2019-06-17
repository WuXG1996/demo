package com.example.demo.pojo;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by void on 2018/11/23.
 */
public class Job {
    @Id
    private String jobId;
    private String title;
    private List<Integer> tag;
    private String num;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getTag() {
        return tag;
    }

    public void setTag(List<Integer> tag) {
        this.tag = tag;
    }
}
