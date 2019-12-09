package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by void on 2018/6/22.
 */
@Slf4j
@Component
public class IntegerConverterConfig implements Converter<String, Integer> {


    @Override
    public Integer convert(String source) {
        long d = System.currentTimeMillis();
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if(value.contains("undefine")){
            //如果前端请求传值undefine直接设置为null
            return null;
        }

        if(!NumberUtils.isCreatable(value)){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            log.error("请求参数字符:{} 转Integer不正确,报错请求url:{},请求参数为：\n{}", source, request.getRequestURL(), JSON.toJSONString(request.getParameterMap()));
            throw new NumberFormatException("参数异常");
        }

        Integer result = Integer.valueOf(value);
//        log.info("============解析Integer类型,数字:{},耗时:{}", value, System.currentTimeMillis()-d);
        return result;
    }

}
