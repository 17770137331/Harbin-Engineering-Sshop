package org.wumbuk.service;

import org.wumbuk.entity.Phones;
import org.wumbuk.entity.User;

/**
 * @auther PeihaoYang
 * @date 2020/12/6 - 16:05
 */

public interface UserService {
    /**
     * 表示用户的注册，注册成功的话就返回用户的用户编号
     * 注册失败的话就返回-1，即该客户已经存在
     * @param user
     * @return
     */
    int register(User user);

    int getUidByPhone(String phone);

    /**
     * 用户通过账号密码登录，如果登录成功，返回用户编号，如何登录不成功，返回-1
     * @param user
     * @return
     */
    int loginByPwd(User user);

    /**
     * 通过用户的uid来查询用户的基本信息
     * @param uid
     * @return
     */
    User getInformation(String uid);

    /**
     * 修改用户的个人信息
     * @param user
     * @return
     */
    int updateUser(User user);



    int recoredPhoneNumber(Phones phones);
}
