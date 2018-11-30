package com.example.demo;

import com.example.demo.pojo.*;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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
        List<User> list =  mongoTemplate.find(query,User.class);
    }

    @Test
    public void test2(){
        User user = new User();
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

        List<User> list = mongoTemplate.find(query, User.class);
        System.out.println(list.size());
    }

    @Test
    public void test4(){
        //mongodbTemplate针对子节点的分页显示
        Query query = new Query();
        query.addCriteria(Criteria.where("commentId").is("5b97835a3590552248af9e56"));
        query.fields().slice("replays", 2, 3);
        Comment comment = mongoTemplate.findOne(query, Comment.class);

        Update update = new Update();
        update.inc("size", -2);

        mongoTemplate.updateFirst(query, update, Comment.class);
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
//            comment.setCommentId(IdGenerate.uuid());
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
    }

    @Test
    public void test10(){
        List<Integer> tag = new ArrayList<>();
        tag.add(9);
        List<JobTo> list = mongoTemplate.find(Query.query(Criteria.where("tag").all(tag)), JobTo.class);
    }
}
