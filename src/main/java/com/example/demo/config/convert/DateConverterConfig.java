package com.example.demo.config.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by void on 2018/6/22.
 * 将前台毫秒时间戳转换为Date类型
 */
@Component
public class DateConverterConfig implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }

        if(source.length() == 13){
            Long time = Long.parseLong(source);
            Timestamp timestamp = new Timestamp(time);
            return timestamp;
        }else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 字符格式化
     * @param source
     * @return
     */
    public Date convert1(String source){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse( source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
