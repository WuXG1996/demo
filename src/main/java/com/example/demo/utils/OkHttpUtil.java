package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by void on 2019/8/28.
 */
@Slf4j
public class OkHttpUtil {

    /**
     * 同步GET
     * @param url
     * @return
     */
    public static String okHttpSyncGet(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //设置链接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                // 设置写数据超时
                .writeTimeout(10, TimeUnit.SECONDS)
                // 设置读数据超时
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        Request request = new Request.Builder().url(urlBuilder.build()).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String str = response.body().string();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 异步GET
     * @param url
     */
    public static void okHttpAsyncGet(String url){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        System.out.println(request);

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("请求异常");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("======call:{}/n response:{}=====", call, response);
                if(!response.isSuccessful()){
                    log.info("======请求不成功======");
                }
                Headers headers = response.headers();
                for(int i=0;i<headers.size();i++){
                    log.info(headers.name(i)+":"+headers.value(i));
                }
                log.info(response.body().string());
            }
        });
    }

    /**
     * 同步post
     * @param url
     * @param postParam
     */
    public static String okHttpSyncPost(String url, String postParam){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, postParam))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //设置链接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                // 设置写数据超时
                .writeTimeout(10, TimeUnit.SECONDS)
                // 设置读数据超时
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String str = response.body().string();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 异步post
     * @param url
     * @param postParam
     */
    public static void okHttpAsyncPost(String url, String postParam){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, postParam))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //设置链接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                // 设置写数据超时
                .writeTimeout(10, TimeUnit.SECONDS)
                // 设置读数据超时
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("失败:{}", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info(response.protocol() + " " +response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    log.info(headers.name(i) + ":" + headers.value(i));
                }
                log.info("onResponse: " + response.body().string());
            }
        });
    }
}
