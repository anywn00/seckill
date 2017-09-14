package com.myimooc.seckill.dto;

/**
 * Created by Arno
 * 2017/9/14.
 * 类的加载顺序
 */
public class StaticTest {

    static StaticTest st = null;
    static{
        System.out.println("静态代码块");
    }
    public StaticTest(){
        //第二步
        System.out.println("构造器");
        System.out.println(st);
        //  m1();
    }
    public static void m1(){
        System.out.println("m1静态方法");
        System.out.println(st);
    }
    {
        //第一步
        System.out.println("普通代码块");
    }
    public static void main(String[] args) {
        st = new StaticTest();
        System.out.println("main开始");
        StaticTest.m1();
        System.out.println("main结束");
    }
}
