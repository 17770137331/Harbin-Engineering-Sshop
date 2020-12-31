package org.wumbuk.service.impl;

import org.wumbuk.dao.FriendMapper;
import org.wumbuk.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther PeihaoYang
 * @date 2020/10/3 - 11:42
 */

@Service("friendService")
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;

    @Override
    public int delete(String phone) {
        return friendMapper.delete(phone);
    }
}
