package com.example.demo.http;

import com.alibaba.fastjson.JSON;
import com.example.demo.mvc.pojo.IUser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * @author void
 * @date 2019/8/14 17:43
 * @desc
 */
public class HttpClientTest {

    //腾讯地图测试key
    private String key = "4HCBZ-Y4SEW-KFVRI-OLL6I-LVJZE-XIFBQ";

    @Test
    public void test1() throws IOException {
        //GET
        String url = "https://apis.map.qq.com/ws/location/v1/ip?key="+key;
        HttpClient httpClient = HttpClientBuilder.create().build();
        //发送get请求
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        /**请求发送成功，并得到响应**/
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            /**读取服务器返回过来的json字符串数据**/
            String strResult = EntityUtils.toString(response.getEntity());
            System.out.println(strResult);
        }
    }

    @Test
    public void test2(){
        //POST
        String url = "http://localhost:8089/save1";

        IUser user = new IUser();
        user.setId(8);
        user.setUsername("void");
        user.setPassword("123");
        user.setBirthday(new Date());
        user.setAddress("龙岗");

        String param = JSON.toJSONString(user);

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity postEntity = new StringEntity(param, "UTF-8");
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = null;
        String res = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            res = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(res);
    }
}
