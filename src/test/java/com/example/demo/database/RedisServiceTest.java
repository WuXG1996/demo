package com.example.demo.database;
import com.example.demo.domain.constant.CommonConstant;
import com.example.demo.domain.constant.CommonConstant.Sex;

import com.example.demo.config.cache.RedisService;
import com.example.demo.domain.entity.excel.TestExcel;
import com.example.demo.mvc.pojo.IUser;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by void on 2020/4/8.
 */
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test1(){
        IUser iUser = redisService.get("user", IUser.class);
    }

    @Test
    public void test2(){
        IUser user1 = new IUser();
        user1.setUsername("tom");
        user1.setBirthday(new Date());
        user1.setPassword("123");

        IUser user2 = new IUser();
        user2.setUsername("john");
        user2.setBirthday(new Date());
        user2.setPassword("123");
        List<IUser> list = Lists.newArrayList(user1, user2);

        redisService.set("users", list);
        List<IUser> result = redisService.list("users", IUser.class);
    }

    @Test
    public void test3(){
        TestExcel testExcel = new TestExcel();
        testExcel.setAge(24);
        testExcel.setTimestamp(new Date().getTime());
        testExcel.setCreateTime(new Date());
        testExcel.setSex1(Sex.Female);
        testExcel.setSex2(Sex.Male);
        testExcel.setSex3(Sex.Female);
        testExcel.setSex4(1);
        testExcel.setResult(false);
        testExcel.setImgUrl("2");
        testExcel.setScore(22.22);
        redisService.set("excel", "excel1",testExcel, 20L, TimeUnit.MINUTES);
    }

    @Test
    public void test4(){
        TestExcel cache = redisService.get("excel::excel1", TestExcel.class);
        TestExcel cache2 = redisService.get("excel","excel1", TestExcel.class);
    }

    @Test
    public void test5(){
        redisService.set("null", null);
        Object object = redisService.get("null", Object.class);
    }

    @Test
    public void test6(){
//        redisService.set("key1", Integer.valueOf(1));
        redisService.set("key2", Long.valueOf(2));
        redisService.set("key3", Short.valueOf("3"));
        redisService.set("key4", 4.4f);
        redisService.set("key5", 2.2d);
        redisService.set("key6", new Character('a'));
        Byte b = 100;
        redisService.set("key7", b);
        redisService.set("key8", true);

        //均为Integer
//        Integer key1 = redisService.get("key1", Integer.class);
//        Long key2 = redisService.get("key2", Long.class);
//        Short key3 = redisService.get("key3", Short.class);
//        Byte key7 = redisService.get("key7", Byte.class);

        //均为BigDecimal
//        Float key4 = redisService.get("key4", Float.class);
//        Double key5 = redisService.get("key5", Double.class);

        //string
//        Character key6 = redisService.get("key6", Character.class);

        //boolean
        Boolean key8 = redisService.get("key8", Boolean.class);
    }

    @Test
    public void test7(){
        Date date = new Date();
//        redisService.set("t", date);
        Date date1 = redisService.get("t", Date.class);
    }
}
