package com.example.demo.database;

import com.alibaba.fastjson.JSON;
import com.example.demo.mvc.pojo.*;
import com.example.demo.mvc.pojo.mongodb.Address;
import com.example.demo.mvc.pojo.mongodb.AddressBO;
import com.example.demo.mvc.pojo.mongodb.Answer;
import com.example.demo.mvc.pojo.mongodb.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by void on 2018/7/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * mongodb模糊查询
     */
    @Test
    public void test1(){
        Query query = new Query();
        Pattern pattern = Pattern.compile("^.*" + "刘" +".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("username").regex(pattern));
        List<IUser> list =  mongoTemplate.find(query,IUser.class);
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 批量插入
     */
    @Test
    public void test2(){
        IUser user = new IUser();
        user.setId(333);
        user.setUsername("wxg");
        user.setPassword("123");
        user.setAddress("江西省宜春市");
        List<Tag> list = new ArrayList<>();

        Tag tag1 = new Tag();
        tag1.setName("标签1");
        tag1.setValue("0");

        Tag tag2 = new Tag();
        tag2.setName("标签2");
        tag2.setValue("1");

        Tag tag3 = new Tag();
        tag3.setName("标签3");
        tag3.setValue("2");

        list.add(tag1);
        list.add(tag2);
        list.add(tag3);
        user.setTags(list);
        mongoTemplate.insert(user);
    }

    /**
     * List<String>节点判断某个字符串存在
     */
    @Test
    public void test3(){
        Query query = new Query();
        query.addCriteria(Criteria.where("phones").is("11111111111"));
        List<IUser> list = mongoTemplate.find(query, IUser.class);
        System.out.println(list.size());
    }

    /**
     * 匹配某个集合范围在List<Object>中对象的某个属性都存在
     * eg:
     * "tags" : [
             {
             "name" : "标签1",
             "value" : "0"
             },
             {
             "name" : "标签2",
             "value" : "1"
             },
             {
             "name" : "标签3",
             "value" : "2"
             }
             ]
     */
    @Test
    public void test4(){
        List<String> list = new ArrayList<String>(){{
            this.add("0");
            this.add("1");
            this.add("2");
        }};
        Query query = new Query();
        query.addCriteria(Criteria.where("tags.value").all(list));
        List<IUser> users = mongoTemplate.find(query, IUser.class);
        System.out.println(users.size());
    }

    /**
     * 修改数组对象第三个元素(索引2)的属性值
     */
    @Test
    public void test5(){
        Query query = new Query();
        query.addCriteria(Criteria.where("password").is("111"));
        Update update = new Update();
        update.set("tags.2.name", "修改后的标签名");
        mongoTemplate.updateFirst(query, update, IUser.class);
    }

    /**
     * 对List<Object>的每一个元素循环对比正确的两个属性值
     */
    @Test
    public void test6(){
        Query query = new Query();
        query.addCriteria(Criteria.where("tags").elemMatch(Criteria.where("value").is("0").andOperator(Criteria.where("name").is("标签1"))));
        boolean result = mongoTemplate.exists(query, IUser.class);
        System.out.println(result);
    }

    /**
     * 针对子节点的分页显示
     * 跳过一条,查询2条
     */
    @Test
    public void test7(){
        Query query = new Query();
        query.fields().slice("tags", 1, 2);
        List<IUser> users = mongoTemplate.find(query, IUser.class);
        System.out.println(users);
    }

    /**
     * 插入或者修改
     */
    @Test
    public void test8(){
        Query query = new Query().addCriteria(Criteria.where("id").is(1));
        Update update = new Update().set("name", "名称2");
        mongoTemplate.upsert(query, update, Role.class);
    }

    /**
     * 查询tags节点索引位置0存在元素的记录
     */
    @Test
    public void test9(){
        List<IUser> list = mongoTemplate.find(Query.query(Criteria.where("tags.0").exists(true)), IUser.class);
    }

    /**
     * 生成数据
     */
    @Test
    public void test10(){
        for(int j=0; j<5; j++){
            List<Replay> list = new ArrayList<>();

            for(int i=0;i<10;i++){
                Replay replay = new Replay();
                replay.setReplayContent("回复"+i);
                replay.setCreateTime(new Date());
                list.add(replay);
            }

            Comment comment = new Comment();
            comment.setContent("测试"+j);
            comment.setReplays(list);

            mongoTemplate.insert(comment);
        }
    }

    @Test
    public void test11(){
        Question question = new Question();
        question.setContent("问题3");

        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer("回复3-1");
        mongoTemplate.insert(answer1);
        answers.add(answer1);

        Answer answer2 = new Answer("回复3-2");
        mongoTemplate.insert(answer2);
        answers.add(answer2);

        question.setAnswers(answers);
        mongoTemplate.insert(question);
    }

    /**
     * 原先为Integer的num字段，设置了数字后
     * 修改类型为String，mongodb还是可以正确映射进来
     * 重新update后会修改db的类型
     */
    @Test
    public void test12(){
        Job job = new Job();
//        job.setNum(100);
//        job.setTitle("测试1");
//        mongoTemplate.insert(job);
        Job job1 = mongoTemplate.findOne(Query.query(Criteria.where("jobId").is("5cf729d32b550f0904dbc2c8")), Job.class);
    }

    /**
     * 新写法记录
     */
    @Test
    public void test13(){
//        long d = System.currentTimeMillis();
//        Query query = new Query();
//        query.addCriteria(Criteria.where("releaseStatus").is(22));
//        query.addCriteria(Criteria.where("releaseSubStatus").is(1));
//        query.addCriteria(Criteria.where("recruitType").is(20));
//        Pattern pattern = Pattern.compile("^" + adCode+ ".*", Pattern.CASE_INSENSITIVE);
//        query.addCriteria(Criteria.where("adCode").regex(pattern));
//
//        GroupBy groupBy = new GroupBy("adCode");
//
//        Document document = new Document();
////        document.put("distinct", "job");
////        document.put("key", "adCode");
//        document.put("groupBy", groupBy.getGroupByObject());
//        document.put("query", query.getQueryObject());
//
//        CommandResult commandResult = mongoTemplate.executeCommand(document.toJson());
//        long d2 = System.currentTimeMillis();
//        System.out.println(d2-d);
//        System.out.println(commandResult.get("values").toString());
    }

    /**
     * 需要连接的集合-本表字段-外表字段-外表连接后的别名
     */
    @Test
    public void test14(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("province").is("广东省")),
                Aggregation.lookup("user", "userId", "userId", "userInfo"),
                Aggregation.unwind("userInfo")
        );
        List<AddressBO> list = mongoTemplate.aggregate(aggregation, Address.class, AddressBO.class).getMappedResults();
        list.forEach(address-> System.out.println(address.toString()));
    }
}
