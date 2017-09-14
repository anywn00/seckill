package com.myimooc.seckill.Exception;

/**
 * Created by Arno
 * 2017/9/11.
 *
 * 秒杀类异常
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
