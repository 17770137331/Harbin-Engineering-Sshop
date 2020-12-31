package org.wumbuk.controller;

import org.wumbuk.dao.GoodMapper;
import org.wumbuk.entity.Good;
import org.wumbuk.entity.Msg;
import org.wumbuk.entity.Ratings;
import org.wumbuk.entity.Sellers;
import org.wumbuk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @auther PeihaoYang
 * @date 2020/12/14 - 23:21
 */
@Controller
@RequestMapping(value="gooddetail",produces="application/json;charset=UTF-8")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class GoodDetailController {



    @Autowired
    GoodService goodService;

    @Autowired
    RatingService ratingService;


    /**
     * 通过商品的id获得某一个商品的具体信息
     * @param good
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getgooddetail")
    @ResponseBody
    public Msg queryGoodDetail( @RequestBody Good good,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("queryGoodDetail");
        System.out.println("获取到达商品的参数信息为"+good);
        Msg msg = new Msg();
        Good good1=goodService.getGoodByGoodId(good.getGoodid(),1);
        System.out.println("后台查询到的信息为"+good1);
        if(null!=good1){
            msg.setCode(0);
            msg.add("gooddetail",good1);
            return msg;
        }
        msg.setCode(1);
        return msg;
    }






    @RequestMapping(value = "getsellerinfo")
    @ResponseBody
    public Msg querySellerInfo( @RequestBody Good good,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("querySellerInfo");
        System.out.println("获取到达商品的参数信息为"+good);
        Msg msg = new Msg();
        Sellers seller= goodService.getSeller(good);
        System.out.println("后台查询到的信息为"+seller);
        if(null!=seller){
            msg.setCode(0);
            msg.add("sellerInfo",seller);
            return msg;
        }
        msg.setCode(1);
        msg.setMsg("没有查询到商家的信息");
        return msg;
    }


    @RequestMapping(value = "getratings")
    @ResponseBody
    public Msg queryRatings( @RequestBody Good good,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("queryRatings");
        System.out.println("获取的商品参数信息为"+good);
        Msg msg = new Msg();
        List<Ratings> ratings=ratingService.getRatingsByGoodid(good.getGoodid());
        System.out.println("后台查询到的评论信息为"+ratings);
        if(null!=ratings){
            msg.setCode(0);
            msg.add("ratings",ratings);
            return msg;
        }
        msg.setCode(1);
        msg.setMsg("没有查询到相关的评论信息");
        return msg;
    }









}
