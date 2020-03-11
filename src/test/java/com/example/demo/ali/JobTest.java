package com.example.demo.ali;

import com.example.demo.mvc.pojo.IUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by void on 2018/11/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test1(){
//        List<Integer> tag = new ArrayList<>();
//        tag.add(1);
//        tag.add(2);
//        tag.add(3);
//        tag.add(4);
//        tag.add(5);
//        tag.add(6);
//        tag.add(7);
//        tag.add(8);
//        tag.add(9);
//        tag.add(10);
//
//        for(int i=0;i<11;i++){
//            JobTo jobTo = new JobTo();
//            jobTo.setTitle("岗位"+(i+1));
//
//            int num = new Random().nextInt(10);
//            List<Integer> tagList = new ArrayList<>();
//            for(int j=0;j<num;j++){
//                if(tag.get(j)==9){
//                    tagList.add(tag.get(j));
//                    tagList.add(30);
//                }else if(tag.get(j)==8){
//                    tagList.add(tag.get(j));
//                    tagList.add(20);
//                }else if(tag.get(j)==7){
//                    tagList.add(tag.get(j));
//                    tagList.add(10);
//                }else{
//                    tagList.add(tag.get(j));
//                    tagList.add(1);
//                }
//            }
//            jobTo.setTag(tagList);
//            jobTo.setCreateTime(new Date());
//            mongoTemplate.insert(jobTo);//title:'岗位'&&kvpairs=query_tags:9=1:8=1:7=1
//        }
    }


    @Test
    public void test4(){
        Query query = new Query().addCriteria(Criteria.where("username").is("wxg"));
        Update update = new Update();
        update.inc("status", 1);
        mongoTemplate.updateMulti(query, update, IUser.class);
    }
}
