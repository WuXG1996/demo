package com.example.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.mvc.dao.UserDao;
import com.example.demo.mvc.pojo.IUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	
	@Autowired
    private UserDao userDao;
	
	@Test
	public void testInsert(){
        IUser user = new IUser();
		user.setId(1);
		user.setUsername("aaa");
		user.setPassword("123");
		user.setBirthday(new Date());
		
		int result = userDao.insert(user);
		System.out.println(result);
	}
	
	//@Test
    public void testGetById() {
        IUser user = this.userDao.getById(1);
        System.out.println(user.getUsername());
    }
	
	//@Test
    public void testUpdate() {
        IUser user = new IUser();
        user.setId(1);
        user.setPassword("666");
        this.userDao.update(user);
    }
    
    //@Test
    public void testDeleteById() {
        int result = this.userDao.deleteById(1);
        System.out.println(result);
    }
}
