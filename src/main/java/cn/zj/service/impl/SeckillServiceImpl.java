package cn.zj.service.impl;

import cn.zj.dao.seckillDao;
import cn.zj.dao.successkilledDao;
import cn.zj.dto.ExcutionSeckill;
import cn.zj.dto.Exposer;
import cn.zj.dto.SeckillResult;
import cn.zj.enums.seckillEnums;
import cn.zj.exception.RepeatSeckillException;
import cn.zj.exception.SeckillCloseException;
import cn.zj.exception.SeckillException;
import cn.zj.pojo.seckill;
import cn.zj.pojo.successKilled;
import cn.zj.service.seckillService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author zhaojie
 * @date 2018\9\16 0016 - 0:14
 */
@Service
public class SeckillServiceImpl implements seckillService {


    Logger log = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private seckillDao seckilldao;

    @Autowired
    private successkilledDao success;

    //MD5加密的盐值
    private String salt = "lowauopdr09wmf09m3l;jfdlosdjmft3r249`14ic`045ut034u512-30851uc2-035u34v`53`4=69m6b5760o5";

    //查询秒杀商品列表
    public List<seckill> querySeckillList() {

        List<seckill> seckills = seckilldao.queryAll(0, 4);

        return seckills;
    }


    //根据id查询秒杀商品
    public seckill querySeckill(Long seckillId) {

        seckill seckill = seckilldao.queryById(seckillId);

        return seckill;
    }

    //秒杀接口暴露业务
    public Exposer exportSeckillUrl(Long seckillId) {

        seckill seckill = querySeckill(seckillId);

        if(seckill == null){
           //秒杀失败
           return new Exposer(false,seckillId);
        }

        Date startTime =  seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date now =  new Date();

        if( now.getTime() <= startTime.getTime()
                || now.getTime() >= endTime.getTime()){

            return new Exposer(false,seckillId,now.getTime(),startTime.getTime(),endTime.getTime());
        }

        String md5 = getMD5(seckillId);

        return new Exposer(true,md5,seckillId);
    }

    //创建md5
    private String getMD5(Long seckillId){

       String base = salt+"/"+seckillId;
       String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

       return md5;
    }


    //执行秒杀业务   库存减少  + 秒杀成功记录


    @Transactional
    public ExcutionSeckill excuteSeckill(Long seckillId, Long userphone,String md5) {


        try {

            if(md5 == null || !getMD5(seckillId).equals(md5)){

                throw new SeckillException("data rewrite");
            }

            int updateCount = seckilldao.reduceSeckillById(seckillId ,new Date());

            if( updateCount <= 0 ){

                throw  new SeckillCloseException("seckill time end");

            }else {

                int insertCount = 0;

                try{

                    insertCount = success.insertSuccessKilled(seckillId,userphone);

                }catch(Exception ex){

                    throw ex;

                }

                if(insertCount <= 0){

                    throw new RepeatSeckillException("seckill repeat ");

                }else{

                    successKilled successKilled = success.queryByIdWithSeckill(seckillId, userphone);

                    return new ExcutionSeckill(seckillId, seckillEnums.SUCCESS,successKilled);

                }
            }

        }catch (SeckillCloseException e2){

            throw  e2;

        }catch (Exception ex){

            log.info(ex.toString());

            throw new SeckillException(ex.toString());
        }
    }
}
