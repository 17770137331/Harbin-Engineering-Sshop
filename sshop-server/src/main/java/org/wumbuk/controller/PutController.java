package org.wumbuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wumbuk.entity.*;
import org.wumbuk.service.ActivityService;
import org.wumbuk.service.GoodService;
import org.wumbuk.utils.STS;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @auther PeihaoYang
 * 商品发布controller
 * @date 2020/12/19 - 23:14
 */
@Controller
@RequestMapping(value="put",produces="application/json;charset=UTF-8")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class PutController {
    @Autowired
    ActivityService activityService;
    @Autowired
    GoodService goodService;


    /**
     * 该方法用来获得临时密钥，供用户发布商品时图片的上传
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getcredential")
    @ResponseBody
    public Msg getCredentials(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("queryCategories方法执行了");
        Msg msg = new Msg();
        try {
            Credentials sts = STS.STS();
//            String sts=null;
            msg.setCode(0);
            System.out.println("后端得到的密钥信息为"+sts);
            msg.add("credential",sts);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(1);
            msg.setMsg("获得临时密钥失败！！！");
            return msg;
        }
    }


    /**
     * 该方法用来查询状态为0（未开始）和状态1（正进行）的活动集合
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getaliveactivities")
    @ResponseBody
    public Msg getAliveActivies(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" getAliveActivies 方法执行了");
        Msg msg = new Msg();
        try {
            List<Activity> aliveActivitis=activityService.getAliveActivies();

            msg.setCode(0);
            System.out.println("后端得到的合适的活动信息为"+aliveActivitis);
            msg.add("aliveActivities",aliveActivitis);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(1);
            msg.setMsg("获得合适的活动信息失败！！！");
            return msg;
        }
    }



    @RequestMapping(value = "putgood")
    @ResponseBody
    public Msg putGood(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" putGood 方法执行了");
        System.out.println("得到的参数为"+map);
        Msg msg = new Msg();
        String uid=null;
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("用户状态非法");
            msg.setCode(2);
            msg.setMsg("用户状态非法");
            return msg;
        }
        session.setMaxInactiveInterval(60*60*30);
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        boolean uidflag = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("uid")) {
                uidflag=true;
                uid=cookie.getValue();
                cookie.setMaxAge(60*60*30);
                response.addCookie(cookie);
            }
        }
        if(!uidflag) {
            System.out.println("用户状态非法");
            msg.setCode(2);
            msg.setMsg("用户状态非法");
            return msg;
        }

        try {
            int result = goodService.putGood(map,Integer.valueOf(uid));
            if(result==0){
                System.out.println("后端发布商品成功！");
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("发布产品失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }


}
