package org.wumbuk.service.impl;

import org.wumbuk.dao.MessageMapper;
import org.wumbuk.dao.User1Mapper;
import org.wumbuk.entity.Message;
import org.wumbuk.entity.User1;
import org.wumbuk.service.MessageService;
import org.wumbuk.utils.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther PeihaoYang
 * @date 2020/9/24 - 11:49
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageMapper messageMapper;
    @Autowired
    User1Mapper userMapper;

    @Override
    public boolean insertSelective(Message message) {
        int i = messageMapper.insertSelective(message);
        if (i != 0) {
            return true;
        }
        return false;
    }

    @Override
    public int loginBYmessage(Message message) {
        System.out.println("loginBYmessage开始执行了。。。");
        Message messageByPhone = messageMapper.getMessageByPhoneLogin(message.getPhone());

        User1 user = userMapper.selectByPrimaryKey(message.getPhone());
        if (null == user) {
            System.out.println("该用户没有进行注册，没有该用户的记录");
            return 2;
        }
        if (null == messageByPhone) {
            System.out.println("该手机号未发送过验证码");
            return 4;
        }

        System.out.println("查到的验证码是" + messageByPhone.getCode());
        System.out.println("用户输入的验证码是" + message.getCode());
        if (message.getCode().intValue() == messageByPhone.getCode().intValue()) {
            return 1;
        } else {
            return 3;
        }


    }

    @Override
    public Message SendMessage(String phone) {
        Message message = new Message();
        User1 user = userMapper.selectByPrimaryKey(phone);
        if (null == user) {
            System.out.println("该用户没有进行注册，没有该用户的记录");
            message.setRs(false);
            return message;
        }
        return SmsService.SendMessage(phone);
    }

    @Override
    public int RegisterByMessage(Message message) {
        System.out.println("RegisterByMessage开始执行了。。。");
        Message messageByPhone = messageMapper.getMessageByPhoneRegister(message.getPhone());
        System.out.println("查询得到的messageByPhone为"+messageByPhone);
        if (null == messageByPhone) {
            System.out.println("没有该手机号码的记录");
            return 2;
        }

        if (messageByPhone.getCode().intValue() == message.getCode().intValue()) {
            System.out.println("验证成功");
            return 1;
        }
        return 3;
    }

    @Override
    public int delete(String phone) {
        return messageMapper.delete(phone);
    }
}