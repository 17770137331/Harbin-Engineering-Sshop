package org.wumbuk.service;

import org.wumbuk.entity.User1;
import org.wumbuk.utils.GraphicVerificationUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther PeihaoYang
 * @date 2020/9/20 - 20:02
 */
//服务层接口，该接口用来定义与用户基本信息有关的方法
public interface UserService1 {

    //通过用户id和用户psd查询登录是否匹配，如果登录成功，则返回1，如果用户不存在则返回2，如果用户账号密码不匹配返回3
    int login(User1 user) throws Exception;

    //通过用户账号来获取用户的名称


    /**
     *
     * @param user
     * @return 用户昵称
     * 该方法要求我们通过用户的账号可以查询到用户的昵称
     */
    User1 getNameById(User1 user);

    /**
     * 该类用来进行用户的注册
     * @param user
     * @return boolean, 如果注册成功，则就返回true,如果注册失败则就返回false
     */
    boolean register(User1 user) throws Exception;

    User1 getInformationByPhone(String value);

    GraphicVerificationUtil GraphicVerificationCode(HttpServletRequest request);

    int resetPwd(User1 user) throws Exception;

    int updateSelective(User1 user) throws Exception;

    /**
     * 该方法用来在用户登陆后进行重置密码的操作、1表示成功、2表示原密码错误、3表示修改失败
     * @param phone
     * @param newpwd
     * @param oldpwd
     * @return
     */
    int resetPwd2(String phone, String newpwd, String oldpwd) throws Exception;

    int delete(String phone);
}
