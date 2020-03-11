package com.example.demo.database;

import com.alibaba.fastjson.JSON;
import com.example.demo.mvc.pojo.*;
import com.example.demo.mvc.pojo.mongodb.Address;
import com.example.demo.domain.bo.AddressBO;
import com.example.demo.mvc.pojo.mongodb.Answer;
import com.example.demo.mvc.pojo.mongodb.Question;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
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
        //db.iUser.find({},{"tags":{$slice:[1,2]}})
//        Query query = new Query();
//        query.fields().slice("tags", 1, 2);
//        List<IUser> users = mongoTemplate.find(query, IUser.class);
//        System.out.println(users);

        //查询评论
        //db.comment.find({},{"replays":{$slice:[0,2]}})

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
//        Job job = new Job();
//        job.setNum("100");
//        job.setTitle("测试1");
//        mongoTemplate.insert(job);

        Job job = new Job();
        job.setNum(200);
        job.setTitle("测试2");
        mongoTemplate.insert(job);
        List<Job> list = mongoTemplate.findAll(Job.class);
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

    /**
     * 使用lbs功能需要先创建索引
     */
    @Test
    public void lbsTest1(){
        Job job1 = new Job("深圳大学", 113.936501,22.532387);
        Job job2 = new Job("科苑", 113.946458,22.526758);
        Job job3 = new Job("微软科通大厦", 113.946458,22.526758);
        Job job4 = new Job("杜鹃山", 113.935783,22.529711);
        Job job5 = new Job("深大运动广场", 113.938003,22.530405);
        Job job6 = new Job("紫檀轩", 113.932585,22.529433);

        List<Job> list = Lists.newArrayList(job1, job2, job3, job4, job5, job6);
        mongoTemplate.insertAll(list);
    }

    /**
     * 圆形
     * withinSphere方法只能用圆形,内部和within一样使用的是 $geoWithin
     * $geoWithin 不需要空间索引,支持box[矩形],polygon[多边形],circle[圆],sphere[球]
     */
    @Test
    public void lbsTest2(){
        //杜鹃山
        Point center = new Point(113.935729,22.529711);
        Circle circle = new Circle(center, new Distance(1000/1000d, Metrics.KILOMETERS));

        Query query1 = Query.query(Criteria.where("location2d").withinSphere(circle));
        List<Job> list1 = mongoTemplate.find(query1, Job.class);
        list1.forEach(System.out::println);

        Query query2 = Query.query(Criteria.where("location2dSphere").withinSphere(circle));
        List<Job> list2 = mongoTemplate.find(query2, Job.class);
        list2.forEach(System.out::println);
    }

    /**
     * 矩形
     */
    @Test
    public void lbsTest3(){
        //深大运动广场
        Point bottomLeft = new Point(113.938003,22.530405);
        //紫檀轩
        Point topRight = new Point(113.932585,22.529433);
        Box box = new Box(bottomLeft, topRight);

        Query query1 = new Query(Criteria.where("location2d").within(box));
        List<Job> list1 = mongoTemplate.find(query1, Job.class);
        list1.forEach(System.out::println);

        Query query2 = new Query(Criteria.where("location2dSphere").within(box));
        List<Job> list2 = mongoTemplate.find(query2, Job.class);
        list2.forEach(System.out::println);
    }

    /**
     * 附近的
     * $near $nearSphere 都需要创建空间索引
     */
    @Test
    public void lbsTest4(){
        //需要创建索引，官方推荐使用2dsphere,2d索引是早在2.2版本就存在的使用新的能够兼容GeoJSON和传统坐标
        //db.job.createIndex({"location2dSphere":"2dsphere"})
        //db.job.createIndex({"location2d":"2d"})

        //杜鹃山
        Point p = new Point(113.935729,22.529711);

        //控制最大最小查询范围单位为米
        Query query1 = new Query(Criteria.where("location2d").near(p).maxDistance(100000).minDistance(0));
        List<Job> list1 = mongoTemplate.find(query1, Job.class);
        list1.forEach(System.out::println);

        //$near 如果想查询2d球面坐标,需要特定格式查询,否则会报错
//        $geometry: {
//            type: "Point" ,
//                    coordinates: [ <longitude> , <latitude> ]
//        }
//        Query query2 = new Query(Criteria.where("location2dSphere").near(p));
//        List<Job> list2 = mongoTemplate.find(query2, Job.class);
//        list2.forEach(System.out::println);

        //$nearSphere 能同时支持2d传统坐标和2dSphere球面坐标
        Query query3 = new Query(Criteria.where("location2d").nearSphere(p));
        List<Job> list3 = mongoTemplate.find(query3, Job.class);
        list3.forEach(System.out::println);

        Query query4 = new Query(Criteria.where("location2dSphere").nearSphere(p));
        List<Job> list4 = mongoTemplate.find(query4, Job.class);
        list4.forEach(System.out::println);
    }

    @Test
    public void lbsTest5(){
        //杜鹃山
        Point p = new Point(113.935729,22.529711);

        Job job1 = new Job("深圳大学", 113.936501,22.532387);
        Job job5 = new Job("深大运动广场", 113.938003,22.530405);
        Job job6 = new Job("乔相阁", 113.933892,22.527399);
        Polygon points = new Polygon(new Point(job1.getLon(), job1.getLat()), new Point(job5.getLon(), job5.getLat()), new Point(job6.getLon(), job6.getLat()));
        Query query = new Query();
        query.addCriteria(Criteria.where("location2dSphere").within(points));
        List<Job> list = mongoTemplate.find(query, Job.class);
        list.forEach(System.out::println);
    }
}
