package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void test9(){
        HashMap<String, Integer> mapA = Maps.newHashMap();
        mapA.put("a", 1);mapA.put("b", 2);mapA.put("c", 3);

        HashMap<String, Integer> mapB = Maps.newHashMap();
        mapB.put("b", 20);mapB.put("c", 3);mapB.put("d", 4);

        MapDifference differenceMap = Maps.difference(mapA, mapB);
        boolean result = differenceMap.areEqual();
        Map entriesDiffering = differenceMap.entriesDiffering();
        Map entriesOnlyLeft = differenceMap.entriesOnlyOnLeft();
        Map entriesOnlyRight = differenceMap.entriesOnlyOnRight();
        Map entriesInCommon = differenceMap.entriesInCommon();

        System.out.println(entriesDiffering);   // {b=(2, 20)}
        System.out.println(entriesOnlyLeft);    // {a=1}
        System.out.println(entriesOnlyRight);   // {d=4}
        System.out.println(entriesInCommon);    // {c=3}
    }

    @Test
    public void test10(){
        Person person = new Person("aa", 14);
        Person ps = new Person("bb", 13);
        Ordering<Person> byOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<Person, String>(){
            public String apply(Person person1){
                return person1.age.toString();
            }
        });
        byOrdering.compare(person, ps);
        System.out.println(byOrdering.compare(person, ps));
    }

    class Person{
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    @Test
    public void test11(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        for(int i=0; i<1000000; i++){
            //1111
        }
        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(nanos);
    }

    @Test
    public void test12() throws IOException {
        File file = new File("D:/test.txt");
        List<String> list = null;
        try{
            list = Files.readLines(file, Charsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        File to = new File("D:/test2.txt");
        Files.copy(file, to);
        Files.move(to, new File("D:/aa/test2.txt"));
        URL url = Resources.getResource("ehcache.xml");
    }
}
