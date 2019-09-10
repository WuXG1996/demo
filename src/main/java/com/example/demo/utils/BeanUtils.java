package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.TreeMap;

/**
 * @author void
 * @date 2019/9/10 10:35
 * @desc
 */
@Slf4j
public class BeanUtils {

    /**
     * 实体类转TreeMap
     * @param obj
     * @return
     */
    public static TreeMap<String, Object> convertBean2TreeMap(Object obj) {
        if (obj == null) {
            return null;
        }
        TreeMap<String, Object> map = new TreeMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if(null==value){
//                        map.put(key,"");
                    }else{
                        map.put(key,value);
                    }
                }
            }
        } catch (Exception e) {
            log.error("convertBean2Map Error {}" ,e);
        }
        return map;
    }
}
