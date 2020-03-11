package com.example.demo.domain.bo;

import com.example.demo.mvc.pojo.mongodb.Address;
import com.example.demo.mvc.pojo.mongodb.User;
import lombok.Data;

/**
 * Created by void on 2019/7/4.
 */
@Data
public class AddressBO extends Address {

    private User userInfo;
}
