package cn.zj.dao;

import cn.zj.pojo.successKilled;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 15:48
 */
public interface successkilledDao {

    /**
     * 秒杀成功的记录，当返回为0的时候 表示重复秒杀
     * 这里对successkilled表中  seckillid 和 userphone进行联合主键的目的就在于此
     * @param seckillId
     * @param userphone
     * @return
     */


    int insertSuccessKilled(@Param("seckillId") Long seckillId,@Param("userphone") Long userphone);


    /**
     * 根据id查询秒杀成功的明细  并且返回秒杀库存的对象
     *
     * 注意点 ：当只传递 id的话  会出现一个问题   秒杀成功该款产品的记录有很多   还需要添加手机号  加以甄别
     *
     * @param seckillId
     * @return
     */


    successKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId,@Param("userphone") Long userphone);



}
