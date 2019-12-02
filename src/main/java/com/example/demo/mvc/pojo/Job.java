package com.example.demo.mvc.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by void on 2018/11/23.
 */
@Data
public class Job {
    @Id
    private String jobId;
    private String title;
    private List<Integer> tag;
    private Integer num;
}
