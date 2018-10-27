package cn.zj.web;

import cn.zj.dto.ExcutionSeckill;
import cn.zj.dto.Exposer;
import cn.zj.dto.SeckillResult;
import cn.zj.enums.seckillEnums;
import cn.zj.exception.RepeatSeckillException;
import cn.zj.exception.SeckillCloseException;
import cn.zj.exception.SeckillException;
import cn.zj.pojo.seckill;
import cn.zj.service.seckillService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zhaojie
 * @date 2018\9\16 0016 - 12:21
 */

@RequestMapping("/seckill") //秒杀模块
@Controller
public class SeckillController {

    //日志系统
    Logger log = LoggerFactory.getLogger(SeckillController.class);


    @Autowired
    private seckillService service;


    //秒杀商品列表模块

    @RequestMapping("/list")
    public String seckillList(Model model){

        System.out.println("========================================================");


        List<seckill> list = service.querySeckillList();

        model.addAttribute("list",list);

        return "/list";
    }


    //秒杀商品详细信息

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId ,Model model){

        if(seckillId == null){

            return "redirect:/seckill/list";

        }

        seckill seckill = service.querySeckill(seckillId);

        if (seckill == null){

            return "forward:/seckill/list";
        }

        model.addAttribute("seckill",seckill);

        return "/detail";
    }


    //秒杀接口

    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> export(@PathVariable("seckillId") Long seckillId ){

        SeckillResult<Exposer> result = null;


        try {

            Exposer exposer = service.exportSeckillUrl(seckillId);

            result = new SeckillResult<Exposer>(true,exposer);

        }catch (Exception e){

            log.info(e.getMessage(),e);

            result = new SeckillResult<Exposer>(false,e.getMessage());

            return result;
        }

        return result;
    }


    //执行秒杀


    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST
      ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
   public SeckillResult<ExcutionSeckill> excution(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                                                  @CookieValue(value="killPhone",required =false)  Long killphone
                                                  ){

        SeckillResult<ExcutionSeckill> result = null;

        try {


            if(killphone == null){

                return new SeckillResult<ExcutionSeckill>(false,"未注册");

            }

            ExcutionSeckill excutionSeckill = service.excuteSeckill(seckillId, killphone, md5);

            result = new SeckillResult<ExcutionSeckill>(true,excutionSeckill);


        }catch (RepeatSeckillException e3 ){

            ExcutionSeckill excution = new ExcutionSeckill(seckillId,seckillEnums.REPEAT);

            return  new SeckillResult<ExcutionSeckill>(true,excution);

        } catch (SeckillCloseException e2){

            ExcutionSeckill excution = new ExcutionSeckill(seckillId,seckillEnums.END);


            return  new SeckillResult<ExcutionSeckill>(true,excution);


        }catch (Exception e){

           log.info(e.getMessage(),e);

            ExcutionSeckill excution = new ExcutionSeckill(seckillId,seckillEnums.REPEAT);
            return  new SeckillResult<ExcutionSeckill>(true,excution);

        }

        return result;
    }





    //获得系统当前时间
    @RequestMapping(value = "/time/now" , method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> getTimeNow(){

        Date date = new Date();

        return new SeckillResult<Long>(true,date.getTime());
    }

}
