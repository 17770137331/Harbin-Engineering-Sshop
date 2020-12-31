package org.wumbuk.service;

import org.wumbuk.entity.Chatroom;

/**
 * @auther PeihaoYang
 * @date 2020/10/3 - 11:39
 */

public interface ChatRoomService {

    int delete(String phone);

    int insertSelective(Chatroom chatRoom);
}
