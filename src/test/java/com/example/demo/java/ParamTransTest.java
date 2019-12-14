package com.example.demo.java;

import com.example.demo.mvc.pojo.mongodb.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by void on 2019/12/14.
 * 1.基本数据类型+8中基本数据包装类+String(这些对象都是final修饰的,不可修改当前对象的值)都是值传递
 * 2.其他对象传参是引用传递，在子方法中进行内存地址赋值不会改变原本方法的对象指向，但是如果再子方法修改了引用地址对象的属性值会反应到母方法
 *  但是java中只有值传递，每次调用函数时穿的是拷贝出来的值，第二种可以理解为传递的是指针(内存地址)额外拷贝出来的值,
 *  对这些副本进行对象地址赋值是不会影响原先的，但是如果基于这个副本指向的内存对象修改具体属性值会影响到原先
 */
@Slf4j
public class ParamTransTest {

    @Test
    public void integerTest(){
        Integer a = 1;
        Integer b = 2;
        integerMethod(a, b);
        log.info("ParamTransTest.integerTest,a:{},b:{}", a, b);
    }

    public void integerMethod(Integer p1, Integer p2){
        Integer temp = p1;
        p1 = p2;
        p2 = temp;
        log.info("ParamTransTest.integerMethod,p1:{},p2:{}", p1, p2);
    }

    @Test
    public void integerTest2(){
        Integer a = 2;
        System.out.println(a);
        changeInteger(a);
        System.out.println(a);
    }

    public void changeInteger(Integer param){
        param = param + 100;
        System.out.println(param);
    }

    @Test
    public void shortTest(){
        Short a = 1;
        Short b = 2;
        shortMethod(a, b);
        log.info("ParamTransTest.shortTest,a:{},b:{}", a, b);
    }

    public void shortMethod(Short p1, Short p2){
        Short temp = p1;
        p1 = p2;
        p2 = temp;
        log.info("ParamTransTest.shortMethod,p1:{},p2:{}", p1, p2);
    }

    @Test
    public void longTest(){
        Long a = 1L;
        Long b = 2L;
        longMethod(a, b);
        log.info("ParamTransTest.longTest,a:{},b:{}", a, b);
    }

    public void longMethod(Long p1, Long p2){
        Long temp = p1;
        p1 = p2;
        p2 = temp;
        log.info("ParamTransTest.longMethod,p1:{},p2:{}", p1, p2);
    }

    @Test
    public void doubleTest(){
        Double a = 1.1d;
        Double b = 2.2d;
        System.out.println("a:"+a+"  b:"+b);
        doubleMethod(a, b);
        System.out.println("a:"+a+"  b:"+b);
    }

    public void doubleMethod(Double p1, Double p2){
        Double temp = p1;
        p1 = p2;
        p2 = temp;
        System.out.println("p1:"+p1+"  p2:"+p2);
    }

    @Test
    public void floatTest(){
        Float a = 1.1f;
        Float b = 2.2f;
        System.out.println("a:"+a+"  b:"+b);
        floatMethod(a, b);
        System.out.println("a:"+a+"  b:"+b);
    }

    public void floatMethod(Float p1, Float p2){
        Float temp = p1;
        p1 = p2;
        p2 = temp;
        System.out.println("p1:"+p1+"  p2:"+p2);
    }

    @Test
    public void booleanTest(){
        Boolean a = true;
        Boolean b = false;
        System.out.println("a:"+a+"  b:"+b);
        booleanMethod(a, b);
        System.out.println("a:"+a+"  b:"+b);
    }

    public void booleanMethod(Boolean p1, Boolean p2){
        Boolean temp = p1;
        p1 = p2;
        p2 = temp;
        System.out.println("p1:"+p1+"  p2:"+p2);
    }

    @Test
    public void byteTest(){
        Byte a = 65;
        Byte b = 66;
        System.out.println("a:"+a+"  b:"+b);
        byteMethod(a, b);
        System.out.println("a:"+a+"  b:"+b);
    }

    public void byteMethod(Byte p1, Byte p2){
        Byte temp = p1;
        p1 = p2;
        p2 = temp;
        System.out.println("p1:"+p1+"  p2:"+p2);
    }

    @Test
    public void charTest(){
        Character a = 'A';
        Character b = 'B';
        System.out.println("a:"+a+"  b:"+b);
        charMethod(a, b);
        System.out.println("a:"+a+"  b:"+b);
    }

    public void charMethod(Character p1, Character p2){
        Character temp = p1;
        p1 = p2;
        p2 = temp;
        System.out.println("p1:"+p1+"  p2:"+p2);
    }

    @Test
    public void stringTest(){
        String a = "A";
        String b = "B";
        System.out.println("a:"+a+"  b:"+b);
//        stringMethod(a, b);
        changeString(a, b);
        System.out.println("a:"+a+"  b:"+b);
    }

    public void stringMethod(String p1, String p2){
        String temp = p1;
        p1 = p2;
        p2 = temp;
        System.out.println("p1:"+p1+"  p2:"+p2);
    }

    public void changeString(String p1, String p2){
        p1 = "gg";
        p2 = "wp";
        System.out.println("p1:"+p1+"  p2:"+p2);
    }

    @Test
    public void objectTest(){
        User user1 = new User();
        user1.setUsername("wxg");
        User user2 = new User();
        user2.setUsername("void");

        log.info("user1:{},user2:{}", user1, user2);
        objectMethod(user1, user2);
        log.info("user1:{},user2:{}", user1, user2);
    }

    public void objectMethod(User p1, User p2){
        User temp = p1;
        //如果对对象进行修改会影响到方法外面
//        temp.setUsername("wxg2");
        p1 = p2;
        p2 = temp;
        log.info("p1:{},p2:{}", p1, p2);
    }

    @Test
    public void objectTest2(){
        User user = new User();
        user.setUserId(1L);
        user.setUsername("void");
        log.info("user:{}", user);
        change(user);
        log.info("user:{}", user);
    }

    public void change(User user){
        user.setUsername("wxg");
        log.info("user:{}", user);
    }
}
