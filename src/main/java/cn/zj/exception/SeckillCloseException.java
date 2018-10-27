package cn.zj.exception;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 23:55
 */

//秒杀关闭异常
public class SeckillCloseException extends SeckillException{


    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
