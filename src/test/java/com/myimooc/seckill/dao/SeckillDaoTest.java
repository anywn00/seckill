package com.myimooc.seckill.dao;

import com.myimooc.seckill.dto.SeckillResult;
import com.myimooc.seckill.entity.Seckill;
import com.myimooc.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Arno
 * 2017/9/8.
 * <p>
 * SeckillDato测试类
 * 配合spring 和junit整合 junt 启动时加载springIOC 容器
 * spring-test junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;


    @Resource
    private SuccessKilled successKilled;
    //这个是为了测试xml中注入的bean能不能获取到他的属性

    @Test
    public void reduceNum() throws Exception {
        long id = 1000;
        int rowCount = seckillDao.reduceNum(id, new Date());
        System.out.println(rowCount);
    }

    /**
     * Seckill
     *
     * {
         seckillId=1000,
         name='1000元秒杀iphone7',
         number=99,
         startTime=WedSep1317: 43: 08GMT+08: 002017,
         endTime=SunSep2400: 00: 00GMT+08: 002017,
         createTime=ThuSep0716: 55: 22GMT+08: 002017
        }
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }

    /**
     * [
     Seckill{
     seckillId=1000,
     name='1000元秒杀iphone7',
     number=99,
     startTime=WedSep1317: 43: 08GMT+08: 002017,
     endTime=SunSep2400: 00: 00GMT+08: 002017,
     createTime=ThuSep0716: 55: 22GMT+08: 002017
     },
     Seckill{
     seckillId=1001,
     name='500元秒杀ipad2',
     number=200,
     startTime=TueAug2200: 00: 00GMT+08: 002017,
     endTime=WedAug2300: 00: 00GMT+08: 002017,
     createTime=ThuSep0716: 55: 22GMT+08: 002017
     },
     Seckill{
     seckillId=1002,
     name='300元秒杀小米4',
     number=300,
     startTime=TueAug2200: 00: 00GMT+08: 002017,
     endTime=WedAug2300: 00: 00GMT+08: 002017,
     createTime=ThuSep0716: 55: 22GMT+08: 002017
     },
     Seckill{
     seckillId=1003,
     name='200元秒杀红米note',
     number=400,
     startTime=TueAug2200: 00: 00GMT+08: 002017,
     endTime=WedAug2300: 00: 00GMT+08: 002017,
     createTime=ThuSep0716: 55: 22GMT+08: 002017
     }
     ]
     * @throws Exception
     */
    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0,5);
        System.out.println(seckills);
    }

}