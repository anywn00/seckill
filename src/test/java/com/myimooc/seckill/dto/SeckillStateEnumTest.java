package com.myimooc.seckill.dto;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arno
 * 2017/9/11.
 */
public class SeckillStateEnumTest {

    @Test
    public void test1(){
        SeckillStateEnum state = SeckillStateEnum.stateOf(1);
        System.out.println(state.getState() + "|" + state.getStaeInfo());
    }
    @Test
    public void test2(){
        SeckillStateEnum success = SeckillStateEnum.SUCCESS;
        System.out.println(success.getStaeInfo());
    }



}