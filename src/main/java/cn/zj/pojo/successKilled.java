package cn.zj.pojo;

import java.util.Date;
import java.util.List;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 15:34
 */
public class successKilled {

    private Long seckillId;
    private Long userPhone;
    private short state;
    private Date createTime;


    //多对一
    private seckill seckill;

    @Override
    public String toString() {
        return "successKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }

    public cn.zj.pojo.seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(cn.zj.pojo.seckill seckill) {
        this.seckill = seckill;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
