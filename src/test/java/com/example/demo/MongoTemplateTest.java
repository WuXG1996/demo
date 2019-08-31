package com.example.demo;

import com.alibaba.fastjson.JSON;
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
    }

    @Test
    public void test2(){
        IUser user = new IUser();
        user.setId(333);
        user.setUsername("吴相旰");
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

    @Test
    public void test3(){
        List<String> list1 = new ArrayList<>();
        list1.add("0");
        list1.add("1");
        list1.add("2");


        Query query = new Query();
        //测试List<String>这种mongodb结构节点
//        query.addCriteria(Criteria.where("tags").is("标签3"));
        query.addCriteria(Criteria.where("tags.value").all(list1));

        List<IUser> list = mongoTemplate.find(query, IUser.class);
        System.out.println(list.size());
    }

    @Test
    public void test3a(){
        Query query = new Query();
        query.addCriteria(Criteria.where("password").is("123"));
        Update update = new Update();
        update.set("tags.2.name", "1231231");

        mongoTemplate.updateFirst(query, update, IUser.class);
        System.out.println(111);
    }

    @Test
    public void test3b(){
        Query query = new Query();
        query.addCriteria(Criteria.where("tags").elemMatch(Criteria.where("value").is("0").andOperator(Criteria.where("name").is("标签2"))));
        boolean result = mongoTemplate.exists(query, IUser.class);
    }

    @Test
    public void test4b(){
        Query query = new Query();
        query.addCriteria(Criteria.where("password").is("123"));
        Update update = new Update();
        update.set("tags.2.name", "1231231");

        mongoTemplate.updateFirst(query, update, IUser.class);
        System.out.println(111);
    }

    @Test
    public void test4(){
        //mongodbTemplate针对子节点的分页显示
        Query query = new Query();
        query.fields().slice("replays", 2, 3);
        List<Comment> comment = mongoTemplate.find(query, Comment.class);

//        Update update = new Update();
//        update.inc("size", -2);

//        mongoTemplate.updateFirst(query, update, Comment.class);
        System.out.println(111);
    }

    @Test
    public void test5(){
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
    public void test6(){
        List<Comment> list = mongoTemplate.find(new Query(), Comment.class);
        System.out.println(111);
    }

    @Test
    public void test7(){
        Role role = new Role();
        role.setId(1);
        role.setName("测试");
        role.setDescr("描述");
        mongoTemplate.insert(role);
    }

    @Test
    public void test8(){
        Query query = new Query().addCriteria(Criteria.where("id").is(1));
        Update update = new Update().set("name", "名称");
        mongoTemplate.upsert(query, update, Role.class);
    }

    @Test
    public void test9(){
        List<JobTo> list = mongoTemplate.find(Query.query(Criteria.where("tag.0").exists(true)), JobTo.class);
        //提取jobId
        List<String> jobIds = list.stream().map(JobTo::getJobId).collect(Collectors.toList());
    }

    @Test
    public void test10(){
        List<Integer> tag = new ArrayList<>();
        tag.add(9);
        List<JobTo> list = mongoTemplate.find(Query.query(Criteria.where("tag").all(tag)), JobTo.class);
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

    @Test
    public void test12(){
        List<Question> questions = mongoTemplate.findAll(Question.class);
    }

    @Test
    public void test13(){
        //$lookup
        /*Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("job", "jobId", "jobId", "jobInfo")
        );
        List<BasicDBObject> list = mongoTemplate.aggregate(aggregation, JobCollection.class, BasicDBObject.class).getMappedResults();*/

        //需要连接的集合-本表字段-外表字段-外表连接后的别名
    }

    @Test
    public void test14(){
//        Question question = new Question();
//        question.setContent("1111");
//        question.setTest(11);
//        mongoTemplate.insert(question);

        Question question = mongoTemplate.findOne(Query.query(Criteria.where("content").is("1111")), Question.class);
        System.out.println(JSON.toJSONString(question));
        mongoTemplate.updateFirst(Query.query(Criteria.where("content").is("1111")), Update.update("test", 11.0), Question.class);
    }

    @Test
    public void test15(){
        //原先为Integer的num字段，设置了数字后，修改类型为String，mongodb还是可以正确映射进来，重新update后会修改db的类型
        Job job = new Job();
//        job.setNum(100);
//        job.setTitle("测试1");
//        mongoTemplate.insert(job);
        Job job1 = mongoTemplate.findOne(Query.query(Criteria.where("jobId").is("5cf729d32b550f0904dbc2c8")), Job.class);
    }

    @Test
    public void test16(){
        //新写法记录
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

    @Test
    public void test17(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("province").is("广东省")),
                Aggregation.lookup("user", "userId", "userId", "userInfo"),
                Aggregation.unwind("userInfo")
        );
        List<AddressBO> list = mongoTemplate.aggregate(aggregation, Address.class, AddressBO.class).getMappedResults();
        list.forEach(address-> System.out.println(address.toString()));
    }
}
