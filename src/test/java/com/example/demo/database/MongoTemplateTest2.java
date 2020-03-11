package com.example.demo.database;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author void
 * @date 2020/3/11 17:50
 * @desc 第二个mongoTemplate测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTemplateTest2 {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test1(){
        long d = System.currentTimeMillis();
        //可执行的命令json格式
        String json = "...";
        BasicDBObject bson = new BasicDBObject();
        bson.put("$eval", json);
        Document commandResult = mongoTemplate.getDb().runCommand(bson);
        System.out.println("耗时:"+(System.currentTimeMillis()-d));
        System.out.println(JSON.toJSONString(commandResult));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(commandResult));
        JSONObject retval = jsonObject.getJSONObject("retval");
        JSONArray batch = retval.getJSONArray("_batch");
        List<String> adCodeList = new ArrayList<>();
        for (Object o : batch) {
            adCodeList.add(((JSONObject) o).getString("_id"));
        }
        System.out.println(111);
    }
}
