package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.User;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String phone);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from user where userid=#{uid}")
    User selectByUid(String uid);

    @Select("select phone from user where userid=#{userid}")
    String getPhoneByUserId(Integer userid);
}