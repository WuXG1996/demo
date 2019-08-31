package com.example.demo.mvc.pojo;

import java.util.Date;

/**
 * Created by void on 2018/9/11.
 */
public class Replay {

    private String replayContent;
    private Date createTime;

    public String getReplayContent() {
        return replayContent;
    }

    public void setReplayContent(String replayContent) {
        this.replayContent = replayContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
