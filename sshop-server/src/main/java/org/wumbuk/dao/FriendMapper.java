package org.wumbuk.dao;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.wumbuk.entity.Friend;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);

    @Delete("delete from friend where uid=#{phone} or fid=#{phone}")
    int delete(String phone);
}