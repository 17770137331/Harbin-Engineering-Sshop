package org.wumbuk.service.impl;

import org.wumbuk.dao.MessageMapper;
import org.wumbuk.dao.User1Mapper;
import org.wumbuk.entity.User1;
import org.wumbuk.service.UserService1;
import org.wumbuk.utils.GraphicVerificationUtil;
import org.wumbuk.utils.SecurePwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther PeihaoYang
 * @date 2020/9/20 - 19:55
 */
//服务层接口的实现类，该类用来完成有关于用户信息的一些服务的操作
@Service("user1Service")
public class UserService1Impl implements UserService1 {


    @Autowired
    User1Mapper userMapper;

    @Autowired
    MessageMapper messageMapper;
    @Override
    public int login(User1 user) throws Exception {
        System.out.println("得到的用户信息为"+user);
        System.out.println("******************");
        System.out.println("后来查询到的用户的密码为");
        String psd2= userMapper.getPsdByPhone(user.getPhone());
        System.out.println(psd2);
        System.out.println(SecurePwd.encrypt(user.getPwd()).trim());
//通过trim()方法，我们删除了查询到的记录的头尾的空白符，只有这样，才能让两个进行比较
        if(psd2==null){
            System.out.println("无此账号");
            return 2;
        }
        if(psd2.trim().equals(SecurePwd.encrypt(user.getPwd()).trim())){
            return 1;
        }else if(psd2==null){
            System.out.println("无此账号");
            return 2;
        }else{
            System.out.println("账号密码不匹配");
            return 3;
        }



    }

    @Override
    public User1 getNameById(User1 user) {
        return null;
    }

    @Override
    public boolean register(User1 user) throws Exception {
        System.out.println("***********************");

        user.setPwd(SecurePwd.encrypt(user.getPwd()));
        int rs=userMapper.insertSelective(user);
        System.out.println("service层要注册的用户的信息为：");
        System.out.println(user);
        System.out.println("得到的flag的结果为"+rs);
        if(rs==1){
            return true;
        }
        return false;

    }

    @Override
    public User1 getInformationByPhone(String value) {
        User1 user = userMapper.selectByPrimaryKey(value);
        System.out.println("service层得到的user信息为"+user);
        return user;
    }

    /**
     * 该类用来返回所生成的验证码的内容
     *
     * @return
     */
    @Override
    public GraphicVerificationUtil GraphicVerificationCode(HttpServletRequest request) {
        GraphicVerificationUtil graphic = GraphicVerificationUtil.Graphic(request);
        System.out.println("得到的图形验证码的内容为"+graphic);
        return graphic;
    }

    @Override
    public int resetPwd(User1 user) throws Exception {
        System.out.println("userService层得到的修改信息为");
        System.out.println(user);
        String encrypt = SecurePwd.encrypt(user.getPwd());
        User1 user1 = userMapper.selectByPrimaryKey(user.getPhone());
        if (null==user1){
            System.out.println("查到的该用户不存在");
            return 2;
        }
        if(user1.getPwd().equals(encrypt)){
            //表明密码根本就不用来进行修改
            return  1;
        }
        user.setPwd(encrypt);
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i==1){
            return 1;
        }else {
            return  3;
        }


    }

    @Override
    public int updateSelective(User1 user) throws Exception {

        System.out.println("Service层在修改个人信息时得到的user为"+user);
        System.out.println("service层要填充的user为"+user);
        return userMapper.updateByPrimaryKeySelective(user);
    }


    @Override
    public int resetPwd2(String phone, String newpwd, String oldpwd) throws Exception {
        System.out.println("service层得到的消息为");
        System.out.println("电话号"+phone);
        System.out.println("新密码"+newpwd);
        System.out.println("旧密码"+oldpwd);

        User1 user = userMapper.selectByPrimaryKey(phone);
        if(!SecurePwd.encrypt(oldpwd).equals(user.getPwd())){
            //表示原密码不匹配
            return 2;
        }
        User1 user1 = new User1();
        user1.setPhone(phone);
        user1.setPwd(SecurePwd.encrypt(newpwd));
        int i = userMapper.updateByPrimaryKeySelective(user1);
        if(i==1){
            System.out.println("修改成功");
            return 1;
        }
        return 3;
    }

    @Override
    public int delete(String phone) {
        return userMapper.deleteByPrimaryKey(phone);
    }


}
