package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获处理
 * @author void
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
     * 处理 Exception 类型的异常
     * @param e
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Map<String,Object> defaultExceptionHandler(Exception e) {
//        Map<String,Object> map = new HashMap<>(2);
//        map.put("code", 500);
//        map.put("msg", e.getMessage());
//        return map;
//    }
}
