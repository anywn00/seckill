package com.myimooc.seckill.dao;

import com.myimooc.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Arno
 * 2017/9/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        int rowCount = successKilledDao.insertSuccessKilled(1000,Long.valueOf("13916014102"));
        System.out.println(rowCount);
    }

    /**
     * SuccessKilled
     * {seckillId=1000, userPhone=13916014102, state=-1, createTime=Wed Sep 13 18:18:28 GMT+08:00 2017}
     * @throws Exception
     */
    @Test
    public void queryByIdWithSeckilled() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckilled(1000, Long.valueOf("13916014102"));
        System.out.println(successKilled);
    }

}