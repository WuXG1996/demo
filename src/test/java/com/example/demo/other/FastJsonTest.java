package com.example.demo.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.Date;

/**
 * Created by void on 2020/3/11.
 */
public class FastJsonTest {

    class Model {
        public int id;
        public String name;
        public Date createTime;
    }

    @Test
    public void test1(){
        Model model = new Model();
        model.id = 1001;
        model.name = "gaotie";

        // {"id":1001,"name":"gaotie"}
        String text_normal = JSON.toJSONString(model);

        // [1001,"gaotie"]
        String text_beanToArray = JSON.toJSONString(model, SerializerFeature.BeanToArray);

        // support beanToArray & normal mode
        JSONArray jsonArray = (JSONArray) JSON.parse(text_beanToArray, Feature.SupportArrayToBean);
        System.out.println(jsonArray.toString());
    }

    @Test
    public void test2(){
        JSON json = new JSON() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }
}
