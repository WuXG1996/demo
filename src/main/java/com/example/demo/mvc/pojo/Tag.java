package com.example.demo.mvc.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by void on 2018/7/30.
 */
@Data
@NoArgsConstructor
public class Tag {

    private String name;
    private String value;
    private List<String> data;

    public Tag(String name, String value, List<String> data) {
        this.name = name;
        this.value = value;
        this.data = data;
    }
}
