package cn.zj.exception;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 23:56
 */

//秒杀业务异常
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

}
