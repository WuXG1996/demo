package com.example.demo.mvc.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by void on 2018/11/23.
 */
public class JobTo implements Serializable{

    @Id
    @JSONField(name = "job_id")
    private String jobId;
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "tag")
    private Integer tag;
    @JSONField(name = "create_time")
    private Date createTime;

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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
