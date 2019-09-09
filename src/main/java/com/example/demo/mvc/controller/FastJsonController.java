package com.example.demo.mvc.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mvc.pojo.IUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="FastJson测试",tags={"测试接口"})
@RestController
@RequestMapping("fastjson")
public class FastJsonController {
	
	@ApiOperation("获取用户信息")
	@ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query")
    @GetMapping("/test/{name}")
    public IUser test(@PathVariable("name") String name) {
        IUser user = new IUser();
        user.setId(1);
        user.setUsername(name);
        user.setPassword("jack123");
        user.setBirthday(new Date());
        return user;
    }

    /**
     * 控制某个接口跨域
     * @return
     */
	@ApiOperation("测试")
	@RequestMapping("/test")
	@CrossOrigin(origins="http://localhost:8020")
	public IUser test(){
        IUser user = new IUser();
        
        user.setId(1);
        user.setUsername("jack");
        user.setPassword("jack123");
        user.setBirthday(new Date());
        //int i = 1/0;
        return user;
	}
}
