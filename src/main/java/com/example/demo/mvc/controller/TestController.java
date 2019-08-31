package com.example.demo.mvc.controller;

import com.example.demo.mvc.pojo.IUser;
import com.example.demo.web.aop.SysLog;
import com.example.demo.web.aop.TestAnnotation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import com.example.demo.mvc.dao.UserDao;
import com.example.demo.mvc.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private TestService testService;

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
	public void test(IUser user){
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
		Thread thread = Thread.currentThread();
		try {
			thread.sleep(300L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("TestController.a");
	}

	@TestAnnotation(id = 2, msg = "b")
	@GetMapping("/b")
	public void b(){
		System.out.println("TestController.b");
		System.out.println(111);
	}

    private String key = "4HCBZ-Y4SEW-KFVRI-OLL6I-LVJZE-XIFBQ";

	@GetMapping("/map")
    public void map(){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://apis.map.qq.com/ws/location/v1/ip?key="+key)
                .build();
        System.out.println(request);
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("失败了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("======call:{}/n response:{}=====", call, response);
                if(!response.isSuccessful()){
                    System.out.println(11111111);
                }
                Headers headers = response.headers();
                for(int i=0;i<headers.size();i++){
                    System.out.println(headers.name(i)+":"+headers.value(i));
                }
                System.out.println(response.body().string());
            }
        });
    }

	@GetMapping("/test3")
	public String test3(){
		testService.test3();
		return "ok";
	}

	@GetMapping("/test4")
	public String test4(){
		testService.test4();
		return "ok";
	}
}
