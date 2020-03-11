package com.example.demo.java;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * Created by void on 2018/11/26.
 */
@Slf4j
public class JavaTest {

    /**
     * 字符串连接
     */
    @Test
    public void test1(){
        System.out.println(String.format("(apply_types=%s OR hope_jobs=%s)", "aa", "bb"));
    }

    /**
     * debug hashMap的方法
     */
    @Test
    public void test2(){
        Map<String, Object> map = new HashMap<>(5);
        map.put(null, "aa");
        map.put("22", null);
        map.put("33", "cc");
        map.put("44", "dd");
        map.put("55", "ee");
        map.put("66", "ff");
        map.put("77", "gg");
        map.put("88", "hh");
        map.put("99", "ii");
        map.put("111", "jj");
    }

    /**
     * list可以直接用equals对比数据是否一致
     */
    @Test
    public void test3(){
        List<Integer> list = new ArrayList<>();
        list.add(new Integer(11));
        if(list.equals(new ArrayList<Integer>(){{
            this.add(new Integer(11));
        }})){
            System.out.println(11111);
        }
    }

    /**
     * 执行顺序
     */
    @Test
    public void test4(){
        System.out.println(1111);
        try{
            System.out.println(22222);
            return;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(333333);
        }finally {
            System.out.println(44444);
        }
        System.out.println(5555);
    }

    /**
     * 可以用运行时异常来判断一次http请求是否出现网络问题没得到同步响应
     */
    @Test
    public void test5(){
        System.out.println("开始执行");
        String result = "";
        //something to do
        if(StringUtils.isBlank(result)){
            System.out.println("判断为空停止执行");
            throw new RuntimeException("没有返回结果");
        }
        System.out.println("执行结束");
    }

    /**
     * 16进制转化2进制,10进制
     */
    @Test
    public void test6(){
        Integer a = 0x75;
        System.out.println(Integer.toUnsignedString(a, 2));

        Integer b = Integer.parseUnsignedInt("75", 16);
        System.out.println(Integer.toUnsignedString(b, 2));
        System.out.println(b);
    }

    /**
     * ArrayList的subList返回的是一个ArrayList的视图,内部类,对其所有操作会反馈到原列表
     */
    @Test
    public void test7(){
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);
        List<Integer> subList = list.subList(0, 2);
        subList.clear();
    }

    /**
     * Arrays.asList生成的list不能使用add/remove/clear，因为生成的是Arrays的内部类,没有实现这些方法
     * 且由于这个方法是适配器原理，对原本array的元素修改，会影响生成的list信息
     */
    @Test
    public void test8(){
        Integer[] array = {1, 2, 3, 4};
        List<Integer> list = Arrays.asList(array);
//        list.add(5);
        array[0] = 5;
    }

    /**
     * 不能再foreach循环里做remove和add,必须使用迭代器来操作,不然可能报错
     */
    @Test
    public void test9(){
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
//        for (String s : a) {
//            if("2".equals(s)){
//                a.remove(s);
//            }
//        }
//        System.out.println(a);

        Iterator<String> it = a.iterator();
        while (it.hasNext()){
            String temp = it.next();
            if ("2".equals(temp)){
                it.remove();
            }
        }
        System.out.println(a);
    }

    /**
     * break指定跳出某层循环
     */
    @Test
    public void test10(){
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> list2 = Lists.newArrayList(5, 6, 3, 7);
        A:for(Integer a : list1){
            log.info("一层循环,a:{}", a);
            for(Integer b : list2){
                log.info("a:{},b:{}", a, b);
                if(a.equals(b)){
                    break A;
                }
            }
        }
    }
}
