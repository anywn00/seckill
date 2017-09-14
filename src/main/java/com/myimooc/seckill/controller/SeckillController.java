package com.myimooc.seckill.controller;

import com.myimooc.seckill.Exception.RepeatKillException;
import com.myimooc.seckill.Exception.SeckillCloseException;
import com.myimooc.seckill.Exception.SeckillException;
import com.myimooc.seckill.dto.Exposer;
import com.myimooc.seckill.dto.SeckillExcution;
import com.myimooc.seckill.dto.SeckillResult;
import com.myimooc.seckill.dto.SeckillStateEnum;
import com.myimooc.seckill.entity.Seckill;
import com.myimooc.seckill.service.ISeckillService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Arno
 * 2017/9/13.
 */
@Controller
@RequestMapping("/seckill") //url: /模块/资源/{id}/细分
public class SeckillController {

    public SeckillController(){
        System.out.println("seckillController被初始化");
    }

    private final Logger logger = LoggerFactory.getLogger(SeckillController.class);


    @Autowired
    private ISeckillService iSeckillService;

    @GetMapping(value= "/list")
    public ModelAndView list(Model model){
        List<Seckill> seckillList = iSeckillService.queryList();
        logger.info("seckillList = {}",seckillList);
        model.addAttribute(seckillList);
        return new ModelAndView("list").addObject("seckillList",seckillList);
    }

    @GetMapping(value="/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(null == seckillId){
            //重定向
            return "redirect:/seckill/list";
        }
        Seckill seckill = iSeckillService.getById(seckillId);
        if(seckill == null){
            //请求转发
            return "forward:/seckill/list";
        }
        model.addAttribute("seckil",seckill);
        return "detail";
    }

    @PostMapping(value="/{seckillId}/exporser",produces="{application/json;charset=UTF-8}")
    @ResponseBody
    public SeckillResult<Exposer> exporser(@PathVariable("seckillId")Long seckillId){
        SeckillResult<Exposer> result = null;
        try{
            Exposer exposer = iSeckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @PostMapping(value="/{seckillId}/{md5}/execution",produces = "{application/json;charset=UTF-8}")
    @ResponseBody
    public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId")Long seckillId,
                                                  @PathVariable("md5")String md5,
                                                  @CookieValue(value = "killPhone",required = false) Long phone){
        if(null == phone){
            return new SeckillResult<SeckillExcution>(false,"未注册");
        }
        SeckillExcution seckillExcution = null;
        try {
            seckillExcution = iSeckillService.excuteSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        }catch(SeckillCloseException e1){
            logger.error(e1.getMessage(),e1);
            seckillExcution = new SeckillExcution(seckillId,SeckillStateEnum.END);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        }catch(RepeatKillException e2){
            logger.error(e2.getMessage(),e2);
            seckillExcution = new SeckillExcution(seckillId,SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        }catch(SeckillException e3){
            logger.error(e3.getMessage(),e3);
            seckillExcution = new SeckillExcution(seckillId,SeckillStateEnum.DATA_REWRITE);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            seckillExcution = new SeckillExcution(seckillId,SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        }
    }
    @GetMapping(value = "/time/now")
    @ResponseBody
    public SeckillResult<Long> time(){
        Date date = new Date();
        return new SeckillResult<Long>(true,date.getTime());
    }


}
