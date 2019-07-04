package com.example.demo.dao;

import com.example.demo.pojo.IUser;

public interface UserDao {
    int insert(IUser user);
    
    int deleteById(Integer id);
    
    int update(IUser user);
    
    IUser getById(Integer id);
}
