package com.example.demo.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author void
 * @date 2019/8/14 17:43
 * @desc
 */
@Slf4j
public class OkHttpTest {

    //腾讯地图测试key
    private String key = "4HCBZ-Y4SEW-KFVRI-OLL6I-LVJZE-XIFBQ";

    @Test
    public void test1() throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://apis.map.qq.com/ws/location/v1/ip?key="+key)
                .build();
        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }

    /**
     * junit无法测试异步
     */
    @Test
    public void test2(){
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
}
