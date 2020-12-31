package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wumbuk.entity.User1;
import org.springframework.stereotype.Repository;

//个人理解第一个mapper就是帮助xml配置文件来注入扫描xml中的namespace的，
//repositoty是在三层架构的service层有助于自动注入的
@Mapper   //声明是一个Mapper，与springbootApplication中的MapperScan二选一写上即可
@Repository
public interface User1Mapper {
    int insert(User1 record);

    int deleteByPrimaryKey(String phone);

    int insertSelective(User1 record);

    User1 selectByPrimaryKey(String phone);

    int updateByPrimaryKeySelective(User1 record);

    int updateByPrimaryKey(User1 record);

    @Select("select pwd from user where phone=#{phone}")
    String getPsdByPhone(String phone);

}