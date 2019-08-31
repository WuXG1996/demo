package com.example.demo.http;

import com.example.demo.utils.OkHttpUtil;
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
        System.out.println(OkHttpUtil.okHttpSyncGet("https://apis.map.qq.com/ws/location/v1/ip?key="+key));
    }

    /**
     * junit无法测试异步
     */
    @Test
    public void test2(){
        OkHttpUtil.okHttpAsyncGet("https://apis.map.qq.com/ws/location/v1/ip?key="+key);
    }
}
