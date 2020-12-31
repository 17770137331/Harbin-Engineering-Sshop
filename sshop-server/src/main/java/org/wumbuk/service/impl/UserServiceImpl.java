package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.PhonesMapper;
import org.wumbuk.dao.UserMapper;
import org.wumbuk.entity.Phones;
import org.wumbuk.entity.User;
import org.wumbuk.service.UserService;
import org.wumbuk.utils.SecurePwd;

/**
 * @auther PeihaoYang
 * @date 2020/12/6 - 16:05
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PhonesMapper phonesMapper;

    @Override
    public int register(User user) {
        System.out.println("业务逻辑层开始注册用户");
        try {
            user.setPwd(SecurePwd.encrypt(user.getPwd()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("业务逻辑层出错了");
            return -1;
        }
        System.out.println("接受到的用户的基本信息为"+user.toString());
        int i = userMapper.insertSelective(user);
        try {
            System.out.println("注册成功");
            User user1 = userMapper.selectByPrimaryKey(user.getPhone());
            return user1.getUserid();
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int getUidByPhone(String phone) {
        System.out.println("业务逻辑层开始通过电话号拿uid");
        User user = userMapper.selectByPrimaryKey(phone);
        if(null!=user){
            return  user.getUserid();
        }
        return -1;
    }

    @Override
    public int loginByPwd(User user) {
        User user1 = userMapper.selectByPrimaryKey(user.getPhone());
        try {
            if(null!=user1&&SecurePwd.encrypt(user.getPwd()).equals(user1.getPwd())){
                System.out.println("用户登录成功");
                return user1.getUserid();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("登录的业务逻辑层出错误了");
            return -1;
        }
        return -1;
    }

    @Override
    public User getInformation(String uid) {
        System.out.println("业务逻辑层开始执行getInformation函数");
        System.out.println("得到的userid为"+uid);
        User user = userMapper.selectByUid(uid);
        user.setPwd(null);
        return user;
    }

    @Override
    public int updateUser(User user) {
        System.out.println("服务层 updateUser 函数执行了，得到的user为");
        System.out.println(user);
        String phone=userMapper.getPhoneByUserId(user.getUserid());
        user.setPhone(phone);
        System.out.println("最后要更新的user为");
        System.out.println(user);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int recoredPhoneNumber(Phones phones) {
        return phonesMapper.insert(phones);
    }
}
