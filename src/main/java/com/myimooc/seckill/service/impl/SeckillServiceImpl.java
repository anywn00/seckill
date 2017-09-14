package com.myimooc.seckill.service.impl;

import com.myimooc.seckill.Exception.RepeatKillException;
import com.myimooc.seckill.Exception.SeckillCloseException;
import com.myimooc.seckill.Exception.SeckillException;
import com.myimooc.seckill.dao.SeckillDao;
import com.myimooc.seckill.dao.SuccessKilledDao;
import com.myimooc.seckill.dto.Exposer;
import com.myimooc.seckill.dto.SeckillExcution;
import com.myimooc.seckill.dto.SeckillStateEnum;
import com.myimooc.seckill.entity.Seckill;
import com.myimooc.seckill.entity.SuccessKilled;
import com.myimooc.seckill.service.ISeckillService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Arno
 * 2017/9/11.
 */
@Service("iSeckillService")
public class SeckillServiceImpl implements ISeckillService {

    private static final Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    //md5盐值字符串，用于混淆md5
    private static final String slat = "fbfduigf&*)*(%%@!-dsd_+we-q=23msk`snalksi";
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Override
    public List<Seckill> queryList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = this.getById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();

        //系统的时间
        Date nowTime = new Date();

        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, nowTime, startTime, endTime, seckillId);
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    /**  使用注解控制事务方法的优点：
     *
     * 1：开发团结达成一致约定，明确标注事务方法的编程风格
     * 2：保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3：不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    @Transactional
    public SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5) {
        //这个if不加入try catch中，交给controller同一处理

        if (StringUtils.isBlank(md5) || !StringUtils.equals(md5, getMd5(seckillId))) {
            //信息篡改
            throw new SeckillException("seckill data rewrite");
        }
        Date nowDate = new Date();
        try {
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeat");
            } else {
                int updateCount = seckillDao.reduceNum(seckillId, nowDate);
                //没有更新到记录 库存
                if (updateCount <= 0) {
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckilled(seckillId, userPhone);
                    return new SeckillExcution(SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}
