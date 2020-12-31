package org.wumbuk.service.impl;

import org.wumbuk.dao.ChatroomMapper;
import org.wumbuk.entity.Chatroom;
import org.wumbuk.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther PeihaoYang
 * @date 2020/10/3 - 11:40
 */
@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService {

@Autowired
    ChatroomMapper chatroomMapper;


    @Override
    public int delete(String phone) {
        return chatroomMapper.deleteByPhone(phone);
    }


    @Override
    public int insertSelective(Chatroom chatRoom) {
        return chatroomMapper.insertSelective(chatRoom);
    }
}
