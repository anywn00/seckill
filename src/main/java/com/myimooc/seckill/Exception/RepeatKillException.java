package com.myimooc.seckill.Exception;

/**
 * Created by Arno
 * 2017/9/11.
 *
 * 重复秒杀异常
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
