package com.example.demo.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mvc.pojo.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by void on 2018/6/20.
 */
@RestController
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/save1")
    public IUser save1(@RequestBody IUser rUser){
        IUser user = new IUser();
        user.setId(rUser.getId());
        user.setUsername(rUser.getUsername());
        user.setPassword(rUser.getPassword());
        user.setBirthday(rUser.getBirthday());
        user.setAddress(rUser.getAddress());
        mongoTemplate.insert(user);

        return user;
    }

    @PostMapping("/save2")
    public void save2(){
        String classStr = "{'classId':'1','Students':[{'studentId':'1','name':'zhangsan'}]}";
        JSONObject parseObject = JSON.parseObject(classStr);
        mongoTemplate.insert(parseObject,"class");
    }

    @PostMapping("/update")
    public void update(){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("wu"));
        Update update = Update.update("address","西丽啦啦啦");
        mongoTemplate.upsert(query,update,IUser.class);

        //更新第一条
        //mongoTemplate.updateFirst()

        //更新全部
        //mongoTemplate.updateMulti()
    }

    @GetMapping("/query")
    public List<IUser> query(){
//        Criteria criatira = new Criteria();
//        criatira.andOperator(Criteria.where("userName").is("admin"), Criteria.where("password").is("f818fa8cf51ca364f367f0046bd014ff"));
//        template.find(new Query(criatira), User.class);

        Query query = new Query();
        //query.addCriteria(Criteria.where("username").is("wu").andOperator(Criteria.where("password").is("123")));
        query.addCriteria(Criteria.where("username").regex(".*?\\" +"wu"+ ".*"));

        List<IUser> list =  mongoTemplate.find(query,IUser.class);
        return list;
    }

    @GetMapping("/query2")
    public List<IUser> query2(){
//        Criteria criatira = new Criteria();
//        criatira.andOperator(Criteria.where("userName").is("admin"), Criteria.where("password").is("f818fa8cf51ca364f367f0046bd014ff"));
//        template.find(new Query(criatira), User.class);

        Query query = new Query();
        query.addCriteria(Criteria.where("password").is("123"));
        List<IUser> list =  mongoTemplate.find(query,IUser.class);
        return list;
    }

    @GetMapping("/query3")
    public List<IUser> query3(){
        Criteria criatira = new Criteria();
        criatira.andOperator(Criteria.where("username").is("wu"), Criteria.where("password").is("123"));
        List<IUser> list =  this.mongoTemplate.find(new Query(criatira), IUser.class);
        return list;
    }
}
