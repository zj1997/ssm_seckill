package cn.zj.service;

import cn.zj.dto.ExcutionSeckill;
import cn.zj.dto.Exposer;
import cn.zj.exception.RepeatSeckillException;
import cn.zj.exception.SeckillCloseException;
import cn.zj.exception.SeckillException;
import cn.zj.pojo.seckill;

import java.util.List;

/**
 * @author zhaojie
 * @date 2018\9\15 0015 - 19:03
 */
public interface seckillService {


    /**
     * 查询所有秒杀商品列表
     * @return
     */


    List<seckill> querySeckillList();


    /**
     * 根据秒杀商品的id来查询商品信息
     * @param seckillId
     * @return
     */

    seckill querySeckill(Long seckillId);


    /**
     * 秒杀开始，输出秒杀接口地址
     * 秒杀未开始  输出系统时间和秒杀时间
     * @param seckillId
     */


    Exposer exportSeckillUrl(Long seckillId);


    /**
     * 执行秒杀
     * @param seckillId
     * @param userphone
     */


    ExcutionSeckill excuteSeckill(Long seckillId , Long userphone,String md5)
            throws SeckillException, SeckillCloseException,RepeatSeckillException;

}
