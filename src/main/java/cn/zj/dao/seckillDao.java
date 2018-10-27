package cn.zj.dao;

import cn.zj.pojo.seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 15:37
 */


public interface seckillDao {


    /**
     * 通过秒杀商品的id对商品的库存量减少
     * @param seckillId
     * @param killTime
     * @return
     * 根据 int 的返回值 来判断影响的行数 进而得知商品是否 秒杀成功
     */


    int reduceSeckillById(@Param("seckillId") Long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀商品
     * @param seckillId
     * @return
     */


    seckill queryById(Long seckillId);


    /**
     * 根据偏移量来得到商品列表
     * @param offent
     * @param limit
     * @return
     */

    List<seckill> queryAll(@Param("offent") int offent ,@Param("limit") int limit);



}
