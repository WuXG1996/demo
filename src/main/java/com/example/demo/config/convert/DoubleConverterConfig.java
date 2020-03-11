package com.example.demo.config.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by void on 2018/6/22.
 */
@Component
public class DoubleConverterConfig implements Converter<String, Double> {

    private static Logger logger = LoggerFactory.getLogger(DoubleConverterConfig.class);

    @Override
    public Double convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }

        Double d;
        try{
            d = Double.valueOf(value);
        }catch (Exception e){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            logger.error("请求参数字符转Double不正确,报错请求url:{},传入参数:{}", request.getRequestURL(), value);
            throw new NumberFormatException("参数异常");
        }
        return d;
    }

}
