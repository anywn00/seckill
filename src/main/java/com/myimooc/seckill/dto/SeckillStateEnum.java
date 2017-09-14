package com.myimooc.seckill.dto;

/**
 * Created by Arno
 * 2017/9/11.
 */
public enum SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据被篡改");

    private int state;

    private String staeInfo;

    SeckillStateEnum(int state, String staeInfo) {
        this.state = state;
        this.staeInfo = staeInfo;
    }

    public static SeckillStateEnum stateOf(int index){
        for(SeckillStateEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
    public int getState() {
        return state;
    }

    public String getStaeInfo() {
        return staeInfo;
    }
}
