package com.myimooc.seckill.dto;

import com.myimooc.seckill.entity.Seckill;

import java.util.Date;

/**
 * Created by Arno
 * 2017/9/11.
 */
public class Exposer {

    //是否要开启秒杀
    private boolean exposed;

    //一种加密措施
    private String md5;

    //当前时间
    private Date now;

    //开启时间
    private Date start;

    //开启结束
    private Date end;

    //秒杀的id
    private long seckillId;


    public Exposer(boolean exposed, Date now, Date start, Date end,long seckillId) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                ", seckillId=" + seckillId +
                '}';
    }
}
