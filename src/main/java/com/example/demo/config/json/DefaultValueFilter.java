package com.example.demo.config.json;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.lang.reflect.Field;

/**
 * Created by void on 2019/11/5.
 * 默认值过滤器
 */
public class DefaultValueFilter implements ValueFilter{
    @Override
    public Object process(Object object, String name, Object value) {
        if(value==null){
            try {
                Field field = object.getClass().getDeclaredField(name);
                switch (field.getGenericType().getTypeName()){
                    case "java.lang.Integer":
                        return "";
                    case "java.lang.Long":
                        return "";
                    case "java.lang.String":
                        return "";
                    case "java.lang.Float":
                        return "";
                    case "java.lang.Double":
                        return "";
                    case "java.lang.Short":
                        return "";
                    case "java.math.BigDecimal":
                        return "";
                    case "java.util.Date":
                        return "";
                    case "java.sql.Date":
                        return "";
                    case "java.time.LocalDateTime":
                        return "*";
                    default:
                        return value;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
