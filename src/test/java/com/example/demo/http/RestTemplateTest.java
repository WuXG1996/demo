package com.example.demo.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.demo.domain.bo.TencentMapResponseBO;
import com.example.demo.mvc.pojo.IUser;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author void
 * @date 2019/8/14 17:44
 * @desc
 */
public class RestTemplateTest {

    //腾讯地图测试key
    private String key = "4HCBZ-Y4SEW-KFVRI-OLL6I-LVJZE-XIFBQ";

    @Test
    public void test1(){
        //GET
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseEntity = template.getForEntity("https://apis.map.qq.com/ws/location/v1/ip?key="+key, String.class);
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        System.out.println(JSON.toJSONString(httpHeaders));

        String body = responseEntity.getBody();
        System.out.println(body);

        HttpStatus httpStatus = responseEntity.getStatusCode();
        System.out.println(httpStatus.is2xxSuccessful()+"==="+httpStatus.value());
    }

    @Test
    public void test2(){
        //GET-带占位符
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity("https://apis.map.qq.com/ws/location/v1/ip?key={key}", String.class, key);
        System.out.println(response.getBody());
    }

    public RestTemplate getTemplateWithFastJson(){
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建fastJson配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>(){{
            add(MediaType.APPLICATION_JSON_UTF8);
        }};
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        RestTemplate template = new RestTemplate();
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        httpMessageConverters.add(fastConverter);
        template.setMessageConverters(httpMessageConverters);
        return template;
    }

    @Test
    public void test3(){
        RestTemplate restTemplate = this.getTemplateWithFastJson();
        TencentMapResponseBO response = restTemplate.getForObject("https://apis.map.qq.com/ws/location/v1/ip?key={key}", TencentMapResponseBO.class, key);
        System.out.println(response.toString());
    }

    @Test
    public void test4(){
        //GET-map替换参数
        RestTemplate restTemplate = this.getTemplateWithFastJson();
        TencentMapResponseBO response = restTemplate.getForObject("https://apis.map.qq.com/ws/location/v1/ip?key={key}", TencentMapResponseBO.class, ImmutableMap.of("key", key));
        System.out.println(response.toString());
    }

    @Test
    public void test5(){
        //POST-entity
        RestTemplate restTemplate = this.getTemplateWithFastJson();

        IUser user = new IUser();
        user.setId(5);
        user.setUsername("void");
        user.setPassword("123");
        user.setBirthday(new Date());
        user.setAddress("龙岗");

        //想替换url参数就使用map参数
        ResponseEntity<IUser> responseEntity = restTemplate.postForEntity("http://localhost:8089/save1?key={key}", user, IUser.class, ImmutableMap.of("key", 123));
        System.out.println(responseEntity.toString());
    }

    @Test
    public void test6(){
        //POST-object
        RestTemplate restTemplate = this.getTemplateWithFastJson();

        IUser user = new IUser();
        user.setId(6);
        user.setUsername("void");
        user.setPassword("123");
        user.setBirthday(new Date());
        user.setAddress("龙岗");

        //想替换url参数就使用map参数
        IUser rUser = restTemplate.postForObject("http://localhost:8089/save1?key={key}", user, IUser.class, ImmutableMap.of("key", 123));
        System.out.println(rUser.toString());
    }

    @Test
    public void test7(){
        //GET-exchange
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange("https://apis.map.qq.com/ws/location/v1/ip?key={key}", HttpMethod.GET, null, String.class, key);

        HttpHeaders httpHeaders = responseEntity.getHeaders();
        System.out.println(JSON.toJSONString(httpHeaders));

        String body = responseEntity.getBody();
        System.out.println(body);

        HttpStatus httpStatus = responseEntity.getStatusCode();
        System.out.println(httpStatus.is2xxSuccessful()+"==="+httpStatus.value());
    }

    @Test
    public void test8(){
        //POST-exchange
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        IUser user = new IUser();
        user.setId(7);
        user.setUsername("void");
        user.setPassword("123");
        user.setBirthday(new Date());
        user.setAddress("龙岗");

        HttpEntity httpEntity = new HttpEntity(user, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8089/save1?key={key}", HttpMethod.POST, httpEntity, String.class, "123");
        System.out.println(JSON.toJSONString(responseEntity));
    }
}
