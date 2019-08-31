package com.example.demo.mvc.pojo.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by void on 2019/7/4.
 */
@Data
public class Address {

    @Id
    private String addressId;
    private String province;
    private String city;
    private String area;
    private String detail;
    private Long userId;
}
