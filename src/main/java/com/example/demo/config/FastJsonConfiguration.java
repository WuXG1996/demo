package com.example.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author void
 * @date 2019/4/8 15:15
 * @desc
 */
@Configuration
public class FastJsonConfiguration implements WebMvcConfigurer{

    /**
     * 自定义一个convert,该方式的convert在List<HttpMessageConverter<?>> converters中出于首位
     * 消息转换器的应用规则，会顺序选择符合要求的消费转换器,这样就不会应用默认的jackson实现了
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建fastJson配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse
//                SerializerFeature.WriteMapNullValue
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>(){{
            add(MediaType.APPLICATION_JSON_UTF8);
        }};
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        return new HttpMessageConverters(fastConverter);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter converter : converters){
//            System.out.println(converter);
        }
    }

    /**
     * 该方式也能实现自定义json格式化,但是加了中文处理后就不生效了，据说是springcloud2.0之后的问题，和jdk8使用stream的api生成的
     * 是新的对象值传递有关系，也可以在设置converters之前将jackson的convert remove了，但是我觉得该方式太粗鲁了，不采用
     */
    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }*/
}