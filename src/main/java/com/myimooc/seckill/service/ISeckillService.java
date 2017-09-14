package com.myimooc.seckill.service;

import com.myimooc.seckill.dto.Exposer;
import com.myimooc.seckill.dto.SeckillExcution;
import com.myimooc.seckill.entity.Seckill;

import java.util.List;

/**
 * Created by Arno
 * 2017/9/11.
 *
 *  业务接口：站在“使用者”角度设计接口 *
 *  三个方面：方法定义粒度、参数、返回类型（return 类型/异常）
 */

public interface ISeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> queryList();


    /**
     * 查询单个秒杀产品
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 暴露秒杀地址的接口
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀 << -- 秒杀地址
     * @param seckillId
     * @param userPhone
     * @param md5  <<--只有md5合适了才能暴露秒杀
     * @return
     */
    SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5);

}
