package cn.zj.exception;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 23:54
 */

//重复秒杀异常
public class RepeatSeckillException extends SeckillException{

    public RepeatSeckillException(String message) {
        super(message);
    }

    public RepeatSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
