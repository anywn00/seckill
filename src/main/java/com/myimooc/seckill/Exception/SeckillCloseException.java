package com.myimooc.seckill.Exception;

/**
 * Created by Arno
 * 2017/9/11.
 *
 * 秒杀关闭一场
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
