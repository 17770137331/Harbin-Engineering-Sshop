package org.wumbuk.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wumbuk.entity.Message;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageMapper {
    int insert(Message record);

    int insertSelective(Message record);

    @Select("SELECT  * \n" +
            "FROM message A\n" +
            "WHERE `time` =(\n" +
            "SELECT MAX(TIME) FROM message \n" +
            "WHERE phone=#{phone}\n" +
            ")AND phone=#{phone} AND flag=1")
    Message getMessageByPhoneRegister(String phone);

    @Select("SELECT  * \n" +
            "FROM message A\n" +
            "WHERE `time` =(\n" +
            "SELECT MAX(TIME) FROM message \n" +
            "WHERE phone=#{phone}\n" +
            ")AND phone=#{phone} AND flag=2" )
    Message getMessageByPhoneLogin(String phone);

    @Delete("delete from message where phone=#{phone}")
    int delete(String phone);
}