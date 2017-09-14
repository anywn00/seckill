package com.myimooc.seckill.dto;

import com.myimooc.seckill.entity.SuccessKilled;

/**
 * Created by Arno
 * 2017/9/11.
 */
public class SeckillExcution {

    //秒杀的id
    private long seckillId;

   //秒杀的状态
    private int state;

    //结果描述
    private String stateInfo;

    //秒杀成功的对象
    private SuccessKilled successSeckilled;

    public SeckillExcution(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStaeInfo();
    }

    public SeckillExcution(SeckillStateEnum seckillStateEnum, SuccessKilled successSeckilled) {
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStaeInfo();
        this.successSeckilled = successSeckilled;
    }


    public long getSeckillId() {
        return seckillId;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessSeckilled() {
        return successSeckilled;
    }

    public void setSuccessSeckilled(SuccessKilled successSeckilled) {
        this.successSeckilled = successSeckilled;
    }

    @Override
    public String toString() {
        return "SeckillExcution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successSeckilled=" + successSeckilled +
                '}';
    }
}
