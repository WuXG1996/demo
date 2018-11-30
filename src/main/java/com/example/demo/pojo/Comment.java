package com.example.demo.pojo;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by void on 2018/9/11.
 */
public class Comment {

    @Id
    private String commentId;
    private String content;
    private Integer size;
    private List<Replay> replays;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Replay> getReplays() {
        return replays;
    }

    public void setReplays(List<Replay> replays) {
        this.replays = replays;
    }
}
