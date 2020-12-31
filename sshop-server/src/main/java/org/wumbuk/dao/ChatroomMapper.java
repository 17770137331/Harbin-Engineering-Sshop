package org.wumbuk.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.wumbuk.entity.Chatroom;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ChatroomMapper {
    int deleteByPrimaryKey(String id);

    int insert(Chatroom record);

    int insertSelective(Chatroom record);

    Chatroom selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Chatroom record);

    int updateByPrimaryKey(Chatroom record);

    @Delete("delete from chatroom where creater=#{phone}")
    int deleteByPhone(String phone);

}