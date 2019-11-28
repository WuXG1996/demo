package com.example.demo.mvc.controller;

import com.example.demo.mvc.pojo.IUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags={"测试接口"}, description = "序列化测试")
@RestController
@RequestMapping("/fastjson")
public class FastJsonController {
	
	@ApiOperation(value = "获取用户信息")
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
	@ApiOperation("控制某个接口跨域")
	@GetMapping("/test")
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
