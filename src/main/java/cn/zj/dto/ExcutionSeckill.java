package cn.zj.dto;

import cn.zj.enums.seckillEnums;
import cn.zj.pojo.successKilled;
import com.sun.net.httpserver.Authenticator;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 23:43
 */
//执行秒杀结果集
public class ExcutionSeckill {

    //秒杀商品的id
    private Long seckillId;


    //秒杀状态
    private Integer state;

    //秒杀状态信息
    private String stateInfo;

    //秒杀成功结果
    private successKilled success;

    public ExcutionSeckill(Long seckillId, seckillEnums enums, successKilled success) {
        this.seckillId = seckillId;
        this.state = enums.getState();
        this.stateInfo = enums.getStateInfo();
        this.success = success;
    }

    public ExcutionSeckill(Long seckillId, seckillEnums enums) {
        this.seckillId = seckillId;
        this.state = enums.getState();
        this.stateInfo = enums.getStateInfo();
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    @Override
    public String toString() {
        return "ExcutionSeckill{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", success=" + success +
                '}';
    }

    public successKilled getSuccess() {
        return success;
    }

    public void setSuccess(successKilled success) {
        this.success = success;
    }
}
