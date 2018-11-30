package com.example.demo.dao;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserDao {
    public int insert(User user);
    
    public int deleteById(Integer id);
    
    public int update(User user);
    
    public User getById(Integer id);
}
