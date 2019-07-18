package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author void
 * @date 2019/7/18 9:52
 * @desc
 */
public class GuavaTest {

    @Test
    public void test1(){
        //普通
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        //不可变
        //特点 1.多线程下线程安全 2.不可变集合比可变集合资料利用更有效 3.创建完后不可改变
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");
    }

    @Test
    public void test2(){
        Multimap<String, Integer> map = ArrayListMultimap.create();
        map.put("aa", 11);
        map.put("aa", 22);
        map.put("bb", 11);
        System.out.println(map.get("aa"));
    }

    @Test
    public void test3(){
        Multiset<String> set = HashMultiset.create();
        set.add("11");
        set.add("22");
        set.add("33");
        set.add("11");
        System.out.println(JSON.toJSONString(set));
    }

    @Test
    public void test4(){
        List<String> list = Lists.newArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        String result = Joiner.on("-").join(list);
        System.out.println(result);
    }

    @Test
    public void test5(){
        Map<String, Integer> map = Maps.newHashMap();
        map.put("小明", 22);
        map.put("小红", 23);
        String result = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(result);

        Map<String, String> map2 = Splitter.on(",").withKeyValueSeparator("=").split(result);
    }

    @Test
    public void test6(){
        String str = "1-2-3-4-5-6";
        List<String> list = Splitter.on("-").splitToList(str);

        //去除前面后面空格
        String str2 = "1-2- 3 -4 -5 ";
        List<String> list2 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str2);
    }

    @Test
    public void test7(){
        ImmutableList<String> names = ImmutableList.of("begin", "code", "Guava", "Java");
        Iterable<String> filter = Iterables.filter(names, Predicates.or(Predicates.equalTo("Java"), Predicates.equalTo("Guava")));
        System.out.println(filter);
    }

    @Test
    public void test8(){
        Set set1 = Sets.newHashSet(1 ,2 ,3 , 4, 5);
        Set set2 = Sets.newHashSet(4, 5, 6, 7, 8);

        //并集
        Sets.SetView union = Sets.union(set1, set2);
        System.out.println(union);

        //差集
        Sets.SetView diff = Sets.difference(set1, set2);
        System.out.println(diff);

        //交集
        Sets.SetView intersection = Sets.intersection(set1, set2);
        System.out.println(intersection);
    }

    //https://blog.csdn.net/wwwdc1012/article/details/82228458
    @Test
    public void test9(){

    }
}
