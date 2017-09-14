package com.myimooc.seckill.dao;

import com.myimooc.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Arno
 * 2017/9/7.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细 可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone);


    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象
     * @param seckillId
     * * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckilled(@Param("seckillId")long seckillId,@Param("userPhone") long userPhone);
}
