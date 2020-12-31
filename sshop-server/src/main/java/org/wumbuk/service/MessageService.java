package org.wumbuk.service;

import org.wumbuk.entity.Message;

/**
 * @auther PeihaoYang
 * @date 2020/9/24 - 11:49
 */

public interface MessageService {

    boolean insertSelective(Message message);

    /**
     * 如果返回1则登录成功、如果返回2则无此账号、如果返回3则验证码不匹配,返回4则是未发送短信.
     * @param message
     * @return
     */
    int loginBYmessage(Message message);

    Message SendMessage(String phone);

    /**
     * 用来注册时查询验证码，1表示成功、2表示没有该手机号码的记录、3表示验证码不正确
     * @param message
     * @return
     */
    int RegisterByMessage(Message message);

    int delete(String phone);
}
