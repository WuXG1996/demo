package com.example.demo.pojo.mongodb;

import lombok.Data;

/**
 * Created by void on 2019/7/4.
 */
@Data
public class AddressBO extends Address {

    private User userInfo;
}
