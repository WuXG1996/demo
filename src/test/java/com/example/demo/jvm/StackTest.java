package com.example.demo.jvm;

/**
 * @author void
 * @date 2020/6/14 22:13
 * @desc
 */
public class StackTest {
    
    private int stackLength = 1;
    
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        try {
            stackTest.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:"+stackTest.stackLength);
            throw e;
        }
    }
}
