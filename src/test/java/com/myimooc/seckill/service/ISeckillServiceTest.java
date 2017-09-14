package com.myimooc.seckill.service;

import com.myimooc.seckill.dto.Exposer;
import com.myimooc.seckill.dto.SeckillExcution;
import com.myimooc.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Arno
 * 2017/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class ISeckillServiceTest {

    @Resource
    private ISeckillService iSeckillService;

    @Test
    public void queryList() throws Exception {
        List<Seckill> seckills = iSeckillService.queryList();
        System.out.println(seckills);
    }

    @Test
    public void getById() throws Exception {
        Seckill seckill = iSeckillService.getById(1000);
        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = iSeckillService.exportSeckillUrl(1000);
        System.out.println(exposer);
    }

    @Test
    public void excuteSeckill() throws Exception {
        SeckillExcution seckillExcution = iSeckillService.excuteSeckill(1000, Long.valueOf("13619015103"), "3d84433b5f02d8df16a0343f897fb4c422");
        System.out.println(seckillExcution);
    }

}