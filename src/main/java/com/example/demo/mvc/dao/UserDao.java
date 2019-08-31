package com.example.demo.mvc.dao;

import com.example.demo.mvc.pojo.IUser;

public interface UserDao {
    int insert(IUser user);
    
    int deleteById(Integer id);
    
    int update(IUser user);
    
    IUser getById(Integer id);
}
