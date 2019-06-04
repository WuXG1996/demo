package com.example.demo.controller;

import com.example.demo.web.aop.SysLog;
import com.example.demo.web.aop.TestAnnotation;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.Tag;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@SysLog("hello接口")
    public String hello() {  
        return "Hello World!";  
    }
	
	@RequestMapping("/test")
	@SysLog
	public String test(){
		return "test";
	}
	
	@RequestMapping("/nima")
	@SysLog("nima接口")
	public String nima(){
		return "nima";
	}

	@PostMapping("/testDate")
	public void test(User user){
		userDao.insert(user);
		System.out.println("11111");
	}

	@PostMapping("/aaa")
	public void aaa(@RequestBody Tag tag){
		//js可以直接传{data:['1','2']}这种数组对象,spring自动解析
		System.out.println(1111);
		System.out.println(2222);
		System.out.println(3333);
	}

	@TestAnnotation(id = 1, msg = "a")
	@GetMapping("/a")
	public void a(){
		System.out.println("TestController.a");
	}

	@TestAnnotation(id = 2, msg = "b")
	@GetMapping("/b")
	public void b(){
		System.out.println("TestController.b");
		System.out.println(111);
	}
}
