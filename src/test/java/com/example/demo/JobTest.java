package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.dependencies.com.google.common.collect.Lists;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.search.*;
import com.aliyun.opensearch.search.SearchParamsBuilder;
import com.example.demo.config.OpenSearchConfig;
import com.example.demo.pojo.JobTo;
import com.example.demo.pojo.User;
import com.example.demo.utils.OpenSearchUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by void on 2018/11/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private OpenSearchConfig openSearchConfig;
    @Autowired
    private DocumentClient documentClient;
    @Autowired
    private SearcherClient searcherClient;

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
    public void test2(){
        List<JobTo> list = mongoTemplate.findAll(JobTo.class);
        try {
            OpenSearchUtil.saveOrUpdateList(openSearchConfig.getApplicationName(), openSearchConfig.getTableName(), documentClient, list);
        } catch (OpenSearchClientException e) {
            e.printStackTrace();
        } catch (OpenSearchException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws OpenSearchClientException, OpenSearchException {
        //分页及返回格式
        Config config = new Config(Lists.newArrayList(openSearchConfig.getApplicationName()));
        config.setStart(0);
        config.setHits(10);
        config.setSearchFormat(SearchFormat.JSON);

        SearchParams searchParams = new SearchParams(config);
        searchParams.setQuery("title:'岗位'&&kvpairs=query_tags:9=1:8=1:7=1");

        Rank rank = new Rank();
        rank.setSecondRankName("tag");
        searchParams.setRank(rank);

        Sort sort = new Sort();
        sort.addToSortFields(new SortField("RANK", Order.DECREASE));
        sort.addToSortFields(new SortField("create_time", Order.DECREASE));
        searchParams.setSort(sort);

        SearchParamsBuilder builder = SearchParamsBuilder.create(searchParams);

        String str = this.searcherClient.execute(builder).getResult();
        JSONObject obj = JSONObject.parseObject(str);
        JSONObject result = (JSONObject) obj.get("result");
        JSONArray items = (JSONArray) result.get("items");
        for(int i=0;i<items.size();i++){
            JSONObject jsonObject = items.getJSONObject(i);
            String tag = jsonObject.getString("tag");//"1\t2\t3\t4\t5\t6\t7\t8\t9"
            if(StringUtils.isBlank(tag)){
                jsonObject.remove("tag");
                continue;
            }
            String[] tags = tag.split("\t");
            List<Integer> tagList = new ArrayList<>();
            for (String s : tags) {
                tagList.add(Integer.parseInt(s));
            }
            jsonObject.put("tag", tagList);
        }
        List<JobTo> list = JSONArray.parseArray(items.toJSONString(), JobTo.class);
        for (JobTo jobTo : list) {
            System.out.println(JSON.toJSONString(jobTo));
        }
    }

    @Test
    public void test4(){
        Query query = new Query().addCriteria(Criteria.where("username").is("wxg"));
        Update update = new Update();
        update.inc("status", 1);
        mongoTemplate.updateMulti(query, update, User.class);
    }
}
