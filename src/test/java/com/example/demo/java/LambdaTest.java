package com.example.demo.java;

import com.example.demo.mvc.pojo.Job;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by void on 2019/6/4.
 */
public class LambdaTest {

    //基本语法
    //(parameters)->expression
    //(parameters)->{statements;}

    String[] atp = {"Rafael Nadal", "Novak Djokovic",
            "Stanislas Wawrinka",
            "David Ferrer","Roger Federer",
            "Andy Murray","Tomas Berdych",
            "Juan Martin Del Potro"};
    List<String> players =  Arrays.asList(atp);

    @Test
    public void test1(){
        players.forEach(player-> System.out.println(player+";"));
        players.forEach(System.out::println);
    }

    @Test
    public void test2(){
        //排序
        Comparator<String> sort = (String s1,String s2)->(s1.compareTo(s2));
        Arrays.sort(atp, sort);
        System.out.println(111);
    }

    @Test
    public void test3(){
        List<String> collect = Stream.of("a", "b", "hello")
                .map(str->str.toUpperCase()).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void test4(){
        List<String> collect = new ArrayList<String>(){{
            this.add("a");
            this.add("apple");
            this.add("b");
            this.add("banana");
        }};
        long l = collect.stream().filter(str->{
            System.out.println("循环处理----"+str);
            if(str.contains("a")){
                return true;
            }else{
                return false;
            }
        }).count();
        System.out.println(l);
    }

    @Test
    public void test5(){
        String[] strs = {"apple", "banana", "ice", "water"};
        String str = Arrays.asList(strs).stream().min(Comparator.comparing(string->string.length())).get();
        System.out.println(str);
    }
    
    @Test
    public void test6(){
        List<Job> list = new ArrayList<>();
        Job max = CollectionUtils.isEmpty(list) ? null : list.stream().max(Comparator.comparing(job->job.getNum())).get();
    }

    /**
     * lambda表达式用return来跳过foreach循环
     */
    @Test
    public void test7(){
        players.forEach(player-> {
            if(player.equals("Novak Djokovic")){
                return;
            }
            System.out.println(player+";");
        });
    }

    @Test
    public void test8(){
        Optional<Object> accessTokenCache = Optional.ofNullable("111");
        String str = accessTokenCache.map(a -> {
            return method1();
        }).orElse(method2());
        System.out.println(str);
    }

    private String method1(){
        System.out.println("内部check");
        return "222";
    }

    private String method2(){
        System.out.println("外部check");
        return "333";
    }
}
