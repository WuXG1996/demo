package com.example.demo.mvc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ThymeLeaf模板测试
 * @author void
 *
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
	
	@RequestMapping("/hello")
	public String hello(Map<String,Object> map){
		map.put("msg", "你好Thymeleaf");
		return "hello";
	}
	
	/**
	 * 用于测试fileController
	 * @param map
	 * @return
	 */
	@RequestMapping("/file")
	public String file(Map<String,Object> map){
		return "file";
	}
}
