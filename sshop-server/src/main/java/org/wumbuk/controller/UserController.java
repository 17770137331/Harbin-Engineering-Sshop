package org.wumbuk.controller;


import org.wumbuk.entity.*;
import org.wumbuk.service.*;
import org.wumbuk.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

/**
 * @auther PeihaoYang
 * @date 2020/9/20 - 19:01
 */
//如果用@resController证明是controller层并且返回json格式的字符串
//该注解的作用是相当于@requestbody+@requestController一起使用
@Controller
@RequestMapping("/gateway")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    FriendService friendService;
    @Autowired
    RecordService recordService;

    /**
     * 发送短信
     * @param user
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "sendmessage")
    @ResponseBody
    public Msg sendMessage(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("开始执行了sendmessage的函方法");
        Cookie cookie1=null;
        Msg msg = new Msg();
        System.out.println("得到的用户user信息为");
        System.out.println(user);
        Phones phones = new Phones();
        phones.setNumber(user.getPhone());
        try {
            userService.recoredPhoneNumber(phones);
        } catch (Exception e) {
            msg.setCode(1);
            e.printStackTrace();
            return  msg;
        }
        HttpSession session = request.getSession(true);
        if(session.isNew()){
            System.out.println("该session是新建立的");
        }else {
            System.out.println("该session是后来建立的");
            session.removeAttribute("phone");
            session.removeAttribute("id");
            session.removeAttribute("code");
        }
        Cookie cookie = new Cookie("COOKIESESSIONID", "0");
        cookie.setValue(session.getId());
        System.out.println("session里面的cookie的ID为：" + session.getId());
        cookie.setPath(request.getContextPath() + "/");//设置作用域
        cookie.setMaxAge(60 * 5);
        response.setHeader("Set-Cookie", "SameSite=none");
        cookie.setHttpOnly(true);
        Message message = SmsService.SendMessage(user.getPhone());
        System.out.println("发送信息成功");
        response.setHeader("Set-Cookie", "SameSite=none");
        msg.setCode(0);
        session.setAttribute("phone",user.getPhone());
        session.setAttribute("code",message.getCode());
        System.out.println("发送验证码成功、发送的验证码" + message.getCode());
        response.addCookie(cookie);
        return msg;
    }


    /**
     * 在后端生成图形验证码，
     * 直接把base64形式图片给前端进行返回
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "sendgraphcode")
    @ResponseBody
    public Msg sendgraphcode( HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("开始执行了sendgraphcode方法");
        Cookie cookie=null;
        Msg msg = new Msg();
        HttpSession session = request.getSession(true);
        if(session.isNew()){
            System.out.println("该session是新建立的");
        }else {
            System.out.println("该session是后来建立的");
            session.removeAttribute("phone");
            session.removeAttribute("code");
        }
        try {
            GraphicVerificationUtil graphicVerificationUtil = GraphicVerificationUtil.Graphic(request);
            session.setAttribute("code", graphicVerificationUtil.getsRand().substring(graphicVerificationUtil.getsRand().length() - 4,
                    graphicVerificationUtil.getsRand().length()));
            System.out.println("得到的密码为"+session.getAttribute("code"));
            String imgStr = ImageToBase64Util.getImgStr(graphicVerificationUtil.getGraphicAddress());
            imgStr = ("data:image/jpeg;base64," + imgStr);
            msg.setCode(0);
            msg.setMsg("生成验证码成功");
            msg.add("imgStr", imgStr);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生错误");
            msg.setMsg("内部服务器错误");
            msg.setCode(1);
            return msg;
        }
    }



    /**
     * 注册账号
     * @param map
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Msg register(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //如果之前的短信发送成功、则codeflag的值为true、如果失败则值为false
        System.out.println("Controller一开始接受到的map值为" + map);
        User user = JsonUtils.mapToobj(map, User.class);
        Message message = JsonUtils.mapToobj(map, Message.class);
        //如果不加这句会怎样
        message.setPhone(user.getPhone());
        Msg msg = new Msg();
        Cookie cookie1=null;
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        String sessionId = null;
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("得到session为空指针错误");
            msg.setMsg("验证码错误");
            msg.setCode(2);
            return msg;
        }
        System.out.println("得到的session中的code为"+session.getAttribute("code"));
        System.out.println("得到的session中的phone为"+session.getAttribute("phone"));
        boolean idflag=false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("COOKIESESSIONID")) {
                idflag=true;
                if(session.getId().equals(cookie.getValue())&&message.getCode().equals(session.getAttribute("code"))
                        &&message.getPhone().equals(session.getAttribute("phone")) ){
                    session.setMaxInactiveInterval(60*60*5);
                    cookie.setPath(request.getContextPath() + "/");//设置作用域
                    cookie.setMaxAge(60*60*5);
                    response.setHeader("Set-Cookie", "SameSite=none");
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                }else {
                    System.out.println("验证码错误");
                    msg.setCode(2);
                    msg.setMsg("验证码错误");
                    return msg;
                }
            }
        }
        if(!idflag){
            System.out.println("cookie不存在了，即cookie失效了");
            msg.setCode(2);
            msg.setMsg("验证码错误");
            return msg;
        }
        System.out.println("要注册得到的用户信息为" + user);
        int rs = 0;
        try {
            userService.register(user);
            int uidTemp=userService.getUidByPhone(user.getPhone());
            Cookie cookie2=new Cookie("uid",Integer.toString(uidTemp));
            cookie2.setPath(request.getContextPath() + "/");//设置作用域
            cookie2.setMaxAge(60*60*5);
            response.addCookie(cookie2);
            msg.setCode(0);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册失败，该手机号已经注册");
            msg.setMsg("手机号已注册");
            msg.setCode(1);
            return msg;
        }

    }


    /**
     * 用户通过短信验证码登录
     * @param message
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "loginbymessage")
    @ResponseBody
    public Msg loginByMessage(@RequestBody Message message, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始通过短信加验证码的方式进行登录");
        System.out.println("controller层得到的封装后的message信息为" + message.toString());
        HttpSession session = request.getSession(false);
        Msg msg=new Msg();
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("得到session为空指针错误");
            msg.setMsg("验证码错误");
            msg.setCode(1);
            return msg;
        }
        System.out.println("得到的session中的code为"+session.getAttribute("code"));
        System.out.println("得到的session中的phone为"+session.getAttribute("phone"));
        boolean idflag=false;
        boolean resultflag=false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("COOKIESESSIONID")) {
                idflag=true;
                if(session.getId().equals(cookie.getValue())&&message.getCode().equals(session.getAttribute("code"))
                        &&message.getPhone().equals(session.getAttribute("phone")) ){
                    resultflag=true;
                    session.setMaxInactiveInterval(60*60*5);
                    session.removeAttribute("phone");
                    session.removeAttribute("code");
                    cookie.setPath(request.getContextPath() + "/");//设置作用域
                    cookie.setMaxAge(60*60*5);
                    response.setHeader("Set-Cookie", "SameSite=none");
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                }else {
                    System.out.println("验证码错误");
                    msg.setCode(1);
                    msg.setMsg("验证码错误");
                    return msg;
                }
            }
        }
        if(!idflag){
            System.out.println("cookie不存在了，即cookie失效了");
            msg.setCode(1);
            msg.setMsg("验证码错误");
            return msg;
        }
        if(resultflag!=true){
            System.out.println("验证码输入错误");
            msg.setCode(1);
            msg.setMsg("验证码错误");
            return msg;
        }else {

            int uid=userService.getUidByPhone(message.getPhone());
            if(uid!=-1) {

                System.out.println("验证码正确，登录成功！");
                msg.setMsg("登录成功");
                msg.setCode(0);
                Cookie cookie2 = new Cookie("uid",Integer.toString(uid) );
                cookie2.setPath(request.getContextPath() + "/");//设置作用域
                cookie2.setMaxAge(60 * 60 * 5);
                response.addCookie(cookie2);
                return msg;
            }else {
                System.out.println("后台得到该手机号未注册");
                msg.setCode(2);
                msg.setMsg("手机号未注册啊亲");
                return msg;
            }
        }
    }



    /**
     * 用户通过账号和密码登录+图形验证码
     * @param map
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "loginbypwd")
    @ResponseBody
    public Msg loginbypwd(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie1=null;
        System.out.println("loginbypwd中的map为"+map);
        User user = JsonUtils.mapToobj(map, User.class);
        String code = (String) map.get("code");
        System.out.println("得到的用户为" + user);
        System.out.println("得到的验证码为" + code);
        Msg msg = new Msg();
        HttpSession session = null;
        session = request.getSession(false);
        String sessionId = null;
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("操作非法");
            msg.setMsg("操作非法");
            msg.setCode(3);
            return msg;
        }
        System.out.println("session中存储的密码为"+session.getAttribute("code"));
        if(null!=session.getAttribute("code")&&session.getAttribute("code").toString().toLowerCase().equals(code.toLowerCase())){
            System.out.println("开始执行");
            int rs=userService.loginByPwd(user);
            if(rs!=-1){
                session.removeAttribute("code");
                System.out.println("登录成功！");
                msg.setCode(0);
                msg.setMsg("登录成功");
                session.setMaxInactiveInterval(60 * 30*5);
                session.setAttribute("phone",user.getPhone());
                Cookie cookie2 = new Cookie("uid",Integer.toString(rs) );
                cookie2.setPath(request.getContextPath() + "/");//设置作用域
                cookie2.setMaxAge(60 * 60 * 5);
                response.addCookie(cookie2);
                return msg;
            }else{
                System.out.println("账号密码错误");
                msg.setCode(1);
                msg.setMsg("账号密码错误");
                session.removeAttribute("code");
                return msg;
            }
        }else {
            System.out.println("验证码错误");
            msg.setMsg("验证码填写错误");
            msg.setCode(2);
            return msg;
        }
    }



    /**
     * 获得用户详细信息
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getuser")
    @ResponseBody
    public Msg getUserInformation(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Msg msg = new Msg();
        String uid=null;
        System.out.println("getUserInformation 函数执行了");
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

        if(uidflag){
            System.out.println("Controller得到的uid为"+uid);
            User user=userService.getInformation(uid);
            System.out.println("得到的用户数据为"+user);
            msg.setCode(0);
            msg.add("user",user);
            return msg;
        }else {
            System.out.println("用户状态非法");
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }
    }


    /**
     * 用户退出登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Msg msg = new Msg();
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(0);
        System.out.println("loginOut");
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        boolean idflag = false;
        for (Cookie cookie : cookies) {
            cookie.setPath("/");
            cookie.setMaxAge(0);
            cookie.setValue(null);
            response.addCookie(cookie);
        }
    }




    @RequestMapping(value = "updateprofile")
    @ResponseBody
    public Msg updateProfile(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Msg msg = new Msg();
        String uid=null;
        System.out.println("updateProfile 函数执行了");
        System.out.println("得到的参数为"+user);
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
        if(uidflag){
            user.setUserid(Integer.valueOf(uid));
            int result=userService.updateUser(user);
            System.out.println("得到的result为"+result);
            if(result!=0) {
//                System.out.println("得到的用户数据为" );
                System.out.println("修改信息成功");
                msg.setCode(0);
                return msg;
            }
            msg.setCode(1);
            msg.setMsg("修改失败");
            return msg;
        }else {
            System.out.println("用户状态非法");
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }

    }


















    /**
     * 该方法用来当用户要重新设置密码时发送验证码
     *
     * @param user
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "resetpwdsendmessage")
    @ResponseBody
    public Msg resetPwdSendMessage(@RequestBody User1 user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Msg msg = new Msg();
        System.out.println("得到的用户user信息为");
        System.out.println(user);
        //得到验证码的code内容
        HttpSession session = request.getSession(true);
        if (session.isNew()) {
            System.out.println("session是新建立的");
        } else {
            session.removeAttribute("phone");
            session.removeAttribute("id");
            System.out.println("session是后来建立的");
        }
        Message message = SmsService.SendMessage(user.getPhone());
        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("JSESSIONID", TimeStamp.getTimeStamp("c"));
        System.out.println("session里面的：" + session.getId());
        cookie.setPath(request.getContextPath() + "/");//设置作用域
        cookie.setMaxAge(60 * 60 * 5);
        response.setHeader("Set-Cookie", "SameSite=none");
        cookie.setValue(session.getId());
        cookie.setHttpOnly(true);
        if (!message.isRs()) {
            //如果该该短信发送失败
            System.out.println("短信发送失败");
            msg.setCode(1);
            msg.setMsg("发送验证码失败、手机号不存在");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return msg;
        }
          session.setAttribute("code", message.getCode());
          System.out.println("controller中session获得的验证码为" + session.getAttribute("code"));
          session.setAttribute("phone", user.getPhone());
          response.addCookie(cookie);
          return msg;
    }


    @RequestMapping(value = "resetpwd", method = RequestMethod.POST)
    @ResponseBody
    public Msg resetPwd(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //如果之前的短信发送成功、则codeflag的值为true、如果失败则值为false
        System.out.println("Controller一开始接受到的map值为" + map);
        User1 user = JsonUtils.mapToobj(map, User1.class);
        Message message = JsonUtils.mapToobj(map, Message.class);
        Msg msg = new Msg();
        Cookie cookie1=null;
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        try {
            String sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(1);
            msg.setMsg("未申请验证码或者验证码已过期、请重新申请验证码");
            return msg;
        }
        session.setMaxInactiveInterval(60 * 30);
        boolean idflag = false;
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                   if(cookie.getValue().equals(session.getId())){
                       idflag=true;
                       cookie1=cookie;
                   }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setMsg("未申请验证码或者验证码已过期、请重新申请验证码");
            msg.setCode(1);
            return msg;
        }
        if (!idflag) {
            System.out.println("未申请验证码或者验证码已过期、请重新申请验证码");
            msg.setCode(1);
            msg.setMsg("未申请验证码或者验证码已过期、请重新申请验证码");
            return msg;
        }


        if (!session.getAttribute("phone").equals(user.getPhone())) {
            System.out.println("得到的用户的电话号码和申请的不匹配");
            msg.setCode(2);
            return msg;
        }
        if (!session.getAttribute("code").equals(message.getCode())) {
            System.out.println("验证码不对");
            msg.setCode(2);
            return msg;
        }

        System.out.println("要更新密码后的得到的用户信息为" + user);
int rs=0;
//        int rs = userService.resetPwd(user);
        if (rs == 1) {

            System.out.println("重置密码成功");
            msg.setCode(0);
            msg.setMsg("重置密码成功");
        } else if (rs == 2) {
            System.out.println("该账号不存在");
            msg.setCode(3);
            msg.setMsg("该账号不存在");
        } else {
            System.out.println("重置密码失败，服务器内部错误");
            msg.setCode(3);
            msg.setMsg("重置密码失败，服务器内部错误");
        }
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);
        return msg;
    }


    /**
     * 该方法用来用户修改密码
     *
     * @param map
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "resetpwd2", method = RequestMethod.POST)
    @ResponseBody
    public Msg resetPwd2(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String newpwd = (String) map.get("newpwd");
        String oldpwd = (String) map.get("oldpwd");
        System.out.println("controller接受的新密码是：" + newpwd);
        System.out.println("接受的旧密码是" + oldpwd);
        Msg msg = new Msg();
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        try {
            String sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("长时间未操作、登录过期");
            msg.setCode(2);
            msg.setMsg("长时间未操作、登录过期");
            return msg;
        }
        boolean idflag = false;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                System.out.println("cookie里面的id为" + cookie.getValue());
                System.out.println("session里面的id为" + session.getId());
                if (cookie.getValue().trim().equals(session.getId().trim())) {
                    System.out.println("二者相同");
                    idflag = true;
                }
            }
        }
        if (idflag) {
            String phone = (String) session.getAttribute("phone");
            int rs=1;
//            int rs = userService.resetPwd2(phone, newpwd, oldpwd);
            if (rs == 1) {
                System.out.println("修改成功");
                msg.setCode(0);
                msg.setMsg("修改成功");
            } else if (rs == 2) {
                System.out.println("修改失败,原密码错误");
                msg.setCode(1);
                msg.setMsg("修改失败,原密码错误");
            } else {
                System.out.println("修改失败，服务器内部原因");
                msg.setCode(3);
                msg.setMsg("修改密码失败");
            }
        } else {
            System.out.println("用户登录的账号申请和服务器的session不匹配，需要重新登录");
            msg.setCode(2);
            msg.setMsg("该账号未登录或长时间未操作");
        }
        return msg;

    }


    /**
     * 该方法用来删除用户账号
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Msg delete(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//我的实现思路是     把其余四个表中的有关记录都给删除了
        Msg msg = new Msg();
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        try {
            String sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("长时间未操作、登录过期");
            msg.setCode(1);
            msg.setMsg("长时间未操作、登录过期");
            return msg;
        }
        boolean idflag = false;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                System.out.println("cookie里面的id为" + cookie.getValue());
                System.out.println("session里面的id为" + session.getId());
                if (cookie.getValue().trim().equals(session.getId().trim())) {
                    System.out.println("二者相同");
                    idflag = true;
                }
            }
        }

        if (idflag) {
            String phone = (String) session.getAttribute("phone");
//先将除了user中的记录进行正常的删除
            try {
                int rs1 = chatRoomService.delete(phone);
                int rs2 = friendService.delete(phone);
                int rs3 = messageService.delete(phone);
                int rs4 = recordService.delete(phone);
            } catch (Exception e) {
                e.printStackTrace();
                msg.setCode(2);
                msg.setMsg("删除失败");
                return msg;
            }
            int rs5=1;
//            int rs5 = userService.delete(phone);

            if (rs5 == 1) {
                System.out.println("删除成功");
                msg.setCode(0);
                msg.setMsg("删除成功");
            } else {
                System.out.println("删除失败，服务器内部原因");
                msg.setCode(3);
                msg.setMsg("删除失败");
            }
        }
        return msg;
    }


    @RequestMapping(value = "update")
    @ResponseBody
    public Msg update(@RequestBody User1 user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Msg msg = new Msg();
        //看session的方法，注意在此我使用的是false参数
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("浏览器未和服务器建立会话，需要登录");
            msg.setCode(2);
            msg.setMsg("该账号未登录或长时间未操作");
            return msg;
        }
        System.out.println("开始通过cookie的值来查询某一个人的具体信息");
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        boolean idflag = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                System.out.println("cookie里面的id为" + cookie.getValue());
                System.out.println("session里面的id为" + session.getId());
                if (cookie.getValue().trim().equals(session.getId().trim())) {
                    System.out.println("二者相同");
                    idflag = true;
                }
            }
        }

        if (idflag) {
            String phone=(String)session.getAttribute("phone");
            user.setPhone(phone);
            int rs=1;
//            int rs = userService.updateSelective(user);
            if (rs == 1) {
                System.out.println("修改成功");
                msg.setCode(0);
                msg.setMsg("修改成功");
            } else {
                System.out.println("修改失败");
                msg.setCode(1);
                msg.setMsg("修改失败");
            }


        } else {
            System.out.println("用户登录的账号申请和服务器的session不匹配，需要重新登录");
            msg.setCode(1);
            msg.setMsg("该账号未登录或长时间未操作");
        }
        return msg;

    }


    /**
     * 该方法用来判断用户的状态、即看其是否是正常的状态
     *
     * @param user
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "checkstatus")
    @ResponseBody
    public Msg checkstatus(@RequestBody User1 user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Msg msg = new Msg();
        System.out.println("checkstatus执行了");
        //看session的方法，注意在此我使用的是false参数
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("浏览器未和服务器建立会话，需要登录");
            msg.setCode(1);
            msg.setMsg("该账号未登录或长时间未操作");
            return msg;
        }
        System.out.println("开始通过cookie的值来查询某一个人的具体信息");
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        boolean idflag = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                System.out.println("cookie里面的id为" + cookie.getValue());
                System.out.println("session里面的id为" + session.getId());
                if (cookie.getValue().trim().equals(session.getId().trim())) {
                    System.out.println("二者相同");
                    idflag = true;
                }
                cookie.setMaxAge(60*60*5);
                response.addCookie(cookie);
            }
        }
        if (idflag) {
            msg.setCode(0);
            //对session进行保活
            session.setMaxInactiveInterval(60*60*30);
            msg.setMsg("状态正常！");


        } else {
            System.out.println("用户登录的账号申请和服务器的session不匹配，需要重新登录");
            msg.setCode(1);
            msg.setMsg("该账号未登录或长时间未操作");
        }
        return msg;
    }


}