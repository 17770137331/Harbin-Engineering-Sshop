package org.wumbuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wumbuk.entity.Msg;
import org.wumbuk.entity.Order;

import org.wumbuk.entity.Ratings;
import org.wumbuk.service.OrderService;
import org.wumbuk.service.RatingService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @auther PeihaoYang
 * @date 2020/12/21 - 20:55
 */

@Controller
@RequestMapping(value="order",produces="application/json;charset=UTF-8")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RatingService ratingService;



    /**
     * 根据用户名查询所有的订单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getordersall")
    @ResponseBody
    public Msg queryOrdersAll( HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" queryOrdersAll 方法执行了");
        String uid=null;
        Msg msg = new Msg();
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("用户状态非法");
            msg.setCode(1);
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
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }

        try {
            List<Order> ordersAll = orderService.getOrdersAll(uid);
            if(!ordersAll.isEmpty()){
                System.out.println("后端查询所有订单成功！");
                msg.add("ordersAll",ordersAll);
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("后端查询所有订单失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }







    /**
     * 表示确认订单，即新建订单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "confirmorder")
    @ResponseBody
    public Msg confirmOrder(@RequestBody Order order,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" confirmOrder 方法执行了");
        System.out.println("得到的参数为"+order);
        String uid=null;
        Msg msg = new Msg();
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("用户状态非法");
            msg.setCode(1);
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
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }
        order.setUserbuytitle(uid);
        try {
            int i = orderService.confirmOrder(order);
            if(i!=0){
                System.out.println("后端确认新建订单成功！");
//                msg.add("ordersAll",ordersAll);
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("后端新建订单失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }




    /**
     *根据用户的cookie查询所有进行中的订单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getordersdoing")
    @ResponseBody
    public Msg queryOrdersDoing( HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" queryOrdersDoing 方法执行了");
        String uid=null;
        Msg msg = new Msg();
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("用户状态非法");
            msg.setCode(1);
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
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }

        try {
            List<Order> ordersDoing = orderService.getOrdersDoing(uid);
            if(!ordersDoing.isEmpty()){
                System.out.println("后端查询进行中订单成功！");
                msg.add("ordersDoing",ordersDoing);
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("后端查询进行中的订单失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }



    /**
     *根据用户的cookie查询所有未评价的订单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getordersunrating")
    @ResponseBody
    public Msg queryOrdersUnRating( HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" queryOrdersUnRating 方法执行了");
        String uid=null;
        Msg msg = new Msg();
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("用户状态非法");
            msg.setCode(1);
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
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }

        try {
            List<Order> ordersUnrating = orderService.getOrdersUnrating(uid);
            if(!ordersUnrating.isEmpty()){
                System.out.println("后端查询所有订单成功！");
                msg.add("ordersUnrating",ordersUnrating);
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("后端查询未评价的订单失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }



    /**
     *根据用户的cookie查询所有已经完成的订单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getordersfinished")
    @ResponseBody
    public Msg queryOrdersFinished( HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" queryOrdersFinished 方法执行了");
        String uid=null;
        Msg msg = new Msg();
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("用户状态非法");
            msg.setCode(1);
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
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }

        try {
            List<Order> ordersFinished = orderService.getOrdersFinished(uid);
            if(!ordersFinished.isEmpty()){
                System.out.println("后端查询所有订单成功！");
                msg.add("ordersFinished",ordersFinished);
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("后端查询完成的订单失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }


    /**
     *根据用户点击确认收货
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sendreceiveorder")
    @ResponseBody
    public Msg receiveOrder(@RequestBody Order order,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" receiveOrder 方法执行了,得到的参数如下");
        System.out.println(order);
        String uid=null;
        Msg msg = new Msg();
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("用户状态非法");
            msg.setCode(1);
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
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }
        try {
             int result=orderService.receiveOrder(order);
            if(result!=0){
                System.out.println("后端确认收货成功！");
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("后端确认收货失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }







    @RequestMapping(value = "makerating")
    @ResponseBody
    public Msg makeRating(@RequestBody Ratings ratings, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" makeRating 方法执行了,得到的参数如下");
        System.out.println(ratings);
        String uid=null;
        Msg msg = new Msg();

        try {
            int result=ratingService.makeRating(ratings);
            if(result!=0){
                System.out.println("后端进行评论成功！");
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("后端进行评论失败");
            return msg;
        } catch (Exception e) {
            msg.setCode(1);
            msg.setMsg("后端服务器故障！");
            e.printStackTrace();
        }
        return null;
    }















}
