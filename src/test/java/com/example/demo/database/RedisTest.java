package com.example.demo.database;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mvc.pojo.IUser;
import com.example.demo.mvc.pojo.Tag;
import com.example.demo.mvc.pojo.mongodb.User;
import com.example.demo.utils.BeanUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author void
 * @date 2019/8/31 15:51
 * @desc
 */
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    private static String key1 = "key1";
    private static String key2 = "key2";

    @Test
    public void test1(){
        System.out.println(redisTemplate.hasKey(key1));

//        redisTemplate.delete(key1);

        //序列化给定 key ，并返回被序列化的值
        System.out.println(redisTemplate.dump(key1));

        //多久后过期
        redisTemplate.expire(key1, 7L, TimeUnit.MINUTES);

        //到什么时间点过期
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 2);
        redisTemplate.expireAt(key1, c.getTime());

        //默认秒形式
        System.out.println(redisTemplate.getExpire(key1));
        System.out.println(redisTemplate.getExpire(key1, TimeUnit.MINUTES));
        //不足一小时，就向下取整
        System.out.println(redisTemplate.getExpire(key1, TimeUnit.HOURS));
    }

    @Test
    public void test2(){
        //正则匹配获取缓存集合
//        Set<String> set = redisTemplate.keys("*key*");

        //检查这个集合里的key有多少存在
        List<String> list = ImmutableList.of(key1, key2);
        System.out.println(redisTemplate.countExistingKeys(list));

        //随机返回
//        String obj = (String) redisTemplate.randomKey();

        //强制改名key10存在也被替换
//        redisTemplate.rename(key1, "key10");

        //当key10不存在时替换改名
//        redisTemplate.renameIfAbsent(key1, "key10");

        //获取key的类型
        System.out.println(redisTemplate.type(key1));
    }

    //***********普通string**********

    @Test
    public void test10(){
//        redisTemplate.opsForValue().set(key1, "111");
//        redisTemplate.opsForValue().set(key2, "222");

        redisTemplate.opsForValue().get(key1);
    }

    @Test
    public void test11(){
//        redisTemplate.opsForValue().set(key1, "111", 7L, TimeUnit.MINUTES);

        Map<String, Object> map = new HashMap<>();
        map.put(key1, "333");
        map.put(key2, "444");
        redisTemplate.opsForValue().multiSet(map);

        Set<String> set = redisTemplate.keys("*key*");
        //批量get按顺序获取值
        List<String> list = redisTemplate.opsForValue().multiGet(set);
    }

    @Test
    public void test12(){
        redisTemplate.opsForValue().increment("key3");
        //一次累加多少
        redisTemplate.opsForValue().increment("key3", 20);
    }

    @Test
    public void test13(){
        IUser user = new IUser();
        user.setId(1);
        user.setAddress("深圳");
        user.setBirthday(new Date());
        user.setPhones(new ArrayList<String>(){{
            this.add("11111111111");
            this.add("22222222222");
        }});
        user.setUsername("void");
        redisTemplate.opsForValue().set("user", user);
//        redisTemplate.opsForValue().set("user", JSON.toJSONString(user));
    }

    @Test
    public void test14(){
        IUser user1 = ((JSONObject)redisTemplate.opsForValue().get("user")).toJavaObject(IUser.class);
//        IUser user1 = JSON.parseObject(((String)redisTemplate.opsForValue().get("user")), IUser.class);
        System.out.println(user1);
    }


    //*********************hash******************
    @Test
    public void test20(){
        IUser user = new IUser();
        user.setId(1);
        user.setUsername("wxg");
        user.setPassword("123");
        user.setAddress("广东");
        user.setStatus(0);
        Tag tag1 = new Tag("n1", "v1", null);
        Tag tag2 = new Tag("n2", "v2", null);
        List<Tag> tags = new ArrayList<Tag>(){{
            this.add(tag1);
            this.add(tag2);
        }};
        user.setTags(tags);
        user.setBirthday(new Date());
        redisTemplate.opsForHash().putAll("hash1", BeanUtils.convertBean2TreeMap(user));
    }

    @Test
    public void test21(){
        redisTemplate.opsForHash().put("hash1", "likes", "games");
        Map<String,Object> map = redisTemplate.opsForHash().entries("hash1");
        for (String s : map.keySet()) {
            System.out.println(map.get(s));
        }
        String address = (String) redisTemplate.opsForHash().get("hash1", "address");
        System.out.println(address);
    }

    @Test
    public void test22(){
        redisTemplate.opsForHash().increment("hash1", "id", 1);
        Long len = redisTemplate.opsForHash().lengthOfValue("hash1", "tags");
    }

    @Test
    public void test23(){
        List<Object> list = redisTemplate.opsForHash().multiGet("hash1", ImmutableList.of("address", "id", "tags"));
        list.forEach(System.out::println);
    }

    //*********************list************************
    //列表的头部（左边）或者尾部（右边）
    //头部*******************尾部<--
    @Test
    public void test24(){
        redisTemplate.opsForList().rightPush("list1", 1);
        redisTemplate.opsForList().rightPush("list1", 2);
        redisTemplate.opsForList().leftPush("list1", 3);
        redisTemplate.opsForList().leftPush("list1", 4);
    }

    @Test
    public void test25(){
        redisTemplate.opsForList().leftPop("list1");
        //等于BLPOP
        redisTemplate.opsForList().leftPop("list1", 30L, TimeUnit.SECONDS);
    }

    @Test
    public void test26(){
        Integer number = (Integer) redisTemplate.opsForList().index("list1", 0);
        System.out.println(number);

        List<Integer> list = redisTemplate.opsForList().range("list1", 0, -1);

        //两个索引位置元素都会包含在内
        List<Integer> list2 = redisTemplate.opsForList().range("list1", 0, 1);
    }

    //***************************set******************************
    @Test
    public void test27(){
        redisTemplate.opsForSet().add("set1", 1);
        redisTemplate.opsForSet().add("set1", 2);
        redisTemplate.opsForSet().add("set1", 3);
        redisTemplate.opsForSet().add("set1", 4);
        redisTemplate.opsForSet().add("set1", 3);
        System.out.println(redisTemplate.opsForSet().size("set1"));
        System.out.println(redisTemplate.opsForSet().isMember("set1", 1));
        Set<String> set = redisTemplate.opsForSet().members("set1");
    }

    @Test
    public void test28(){
        Integer num = (Integer) redisTemplate.opsForSet().pop("set1");
        List<Integer> list = redisTemplate.opsForSet().pop("set1", 2);
    }

    @Test
    public void test29(){
        redisTemplate.opsForSet().add("set2", 1,2,3,4,5,6);
        redisTemplate.opsForSet().add("set3", 4,5,6,7,8,9);

        //交集
        Set<String> set1 = redisTemplate.opsForSet().intersect("set2", "set3");
        //差集
        Set<String> set2 = redisTemplate.opsForSet().difference("set2", "set3");
        //并集
        Set<String> set3 = redisTemplate.opsForSet().union("set2", "set3");
    }

    @Test
    public void test30(){
        //返回两个集合中随机数，这两个随机数绝对不一样
        Set<String> set = redisTemplate.opsForSet().distinctRandomMembers("set2", 2);
        for(int i=0;i<50;i++){
            //会有重复情况
            System.out.println(JSON.toJSONString(redisTemplate.opsForSet().randomMembers("set2", 2)));
        }
    }

    //*****************zset*****************
    @Test
    public void test31(){
        //普通加入
        redisTemplate.opsForZSet().add("zset1", "a", 1);
        redisTemplate.opsForZSet().add("zset1", "b", 2);
        redisTemplate.opsForZSet().add("zset1", "c", 3);
        redisTemplate.opsForZSet().add("zset1", "d", 4);
        redisTemplate.opsForZSet().add("zset1", "e", 5);

        //批量加入
        ZSetOperations.TypedTuple typedTuple1 = new DefaultTypedTuple("a",1d);
        ZSetOperations.TypedTuple typedTuple2 = new DefaultTypedTuple("b",2d);
        ZSetOperations.TypedTuple typedTuple3 = new DefaultTypedTuple("c",3d);
        Set<ZSetOperations.TypedTuple> typedTuples = new HashSet<ZSetOperations.TypedTuple>();
        typedTuples.add(typedTuple1);
        typedTuples.add(typedTuple2);
        typedTuples.add(typedTuple3);
        redisTemplate.opsForZSet().add("zset2", typedTuples);
    }

    @Test
    public void test32(){
        //整个zset的size
//        System.out.println(redisTemplate.opsForZSet().zCard("zset2"));
        //指定分数范围的size
//        System.out.println(redisTemplate.opsForZSet().count("zset2", 1, 2));
        //指定某个元素增加某个分数值，可以设置负数
//        redisTemplate.opsForZSet().incrementScore("zset2", "a", 3);


        //通过下标区间获取
//        Set<ZSetOperations.TypedTuple> set2 = redisTemplate.opsForZSet().range("zset2", 0, 2);
        //通过分数获取
        System.out.println(JSON.toJSONString(redisTemplate.opsForZSet().rangeByScore("zset2", 1, 3)));
        //通过分数获取带分数的set集合
//        Set<ZSetOperations.TypedTuple<Integer>> set = redisTemplate.opsForZSet().rangeByScoreWithScores("zset2", 1, 3);
    }

    @Test
    public void test33(){
        System.out.println(redisTemplate.opsForZSet().reverseRangeByScore("zset2", 1,3));
    }

    /**
     * 将图片存入redis
     * @throws IOException
     */
    @Test
    public void test40() throws IOException {
        File file = new File("G:/72054296_p0.png");
        byte[] fileByte = Files.readAllBytes(file.toPath());
        redisTemplate.opsForValue().set("myPic", Base64Utils.encodeToString(fileByte));
    }
    
    @Test
    public void test41(){
        Set<String> set1 = new HashSet<>();
        set1.add("970bfd1b5d744f6dac969661dad6e7c6");
        set1.add("2b9462b9f2ce4b7282f7e63ee752159f");
        set1.add("efa5a809aa0d4251b7970a19b7e7c964");
        set1.add("1dc41fa35f2344ca9cdece80d393215f");
        set1.add("856750212af34815a04bc0c3025e8ffa");
        set1.add("6f4ad69f01bb4c41b627e477462a3509");
        set1.add("eaaec9ce871e4e5ab9e7ae939032514c");
        set1.add("946b855f9a7b4fe49f3b011a49d3722a");
        set1.add("f878992e17f34ce3bae1a7ceb6dd059c");
        set1.add("edac778e489c4518a09951ea430c534e");
        String[] strs1 = new String[set1.size()];
        set1.toArray(strs1);
        redisTemplate.opsForSet().add("set1", strs1);

        Set<String> set2 = new HashSet<>();
        set2.add("970bfd1b5d744f6dac969661dad6e7c6");
        set2.add("2b9462b9f2ce4b7282f7e63ee752159f");
        set2.add("efa5a809aa0d4251b7970a19b7e7c964");
        set2.add("1dc41fa35f2344ca9cdece80d393215f");
        set2.add("856750212af34815a04bc0c3025e8ffa");
        set2.add("6f4ad69f01bb4c41b627e477462a3509");
        set2.add("eaaec9ce871e4e5ab9e7ae939032514c");
        set2.add("946b855f9a7b4fe49f3b011a49d3722a");
        set2.add("f878992e17f34ce3bae1a7ceb6dd059c");
        set2.add("7b554324c3e54724abef165c7577ff4e");
        String[] strs2 = new String[set2.size()];
        set2.toArray(strs2);
        redisTemplate.opsForSet().add("set2", strs2);
    }
    
    @Test
    public void test42(){
        //交集
        Set<String> set1 = redisTemplate.opsForSet().intersect("set1", "set2");
        //差集
        Set<String> set2 = redisTemplate.opsForSet().difference("set1", "set2");
        //并集
        Set<String> set3 = redisTemplate.opsForSet().union("set1", "set2");
        System.out.println(1);
    }
    
    @Test
    public void test43(){
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("wxg1");
        
        User user2 = new User();
        user2.setUserId(2);
        user2.setUsername("wxg2");
        
        redisTemplate.opsForValue().set("string1", user1);
        redisTemplate.opsForValue().set("string2", user2);
        
        redisTemplate.opsForHash().put("hash", "uk1", user1);
        redisTemplate.opsForHash().put("hash", "uk2", user2);
        
        redisTemplate.opsForSet().add("set", user1, user2);
        
        redisTemplate.opsForZSet().add("zset", user1, 1);
        redisTemplate.opsForZSet().add("zset", user2, 2);
    }
    
    @Test
    public void test44(){
        redisTemplate.opsForSet().add("1", 1,2,3);
        String[] strs = {"1", "2", "3"};
        redisTemplate.opsForSet().add("2", strs);
        List<String> list = Lists.newArrayList("1", "2","3");
        redisTemplate.opsForSet().add("3", list.toArray(new String[list.size()]));
    }
}
