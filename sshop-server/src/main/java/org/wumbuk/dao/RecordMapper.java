package org.wumbuk.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.wumbuk.entity.Record;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RecordMapper {
    int insert(Record record);

    int insertSelective(Record record);

    @Delete("delete from record where uid=#{phone}")
    int delete(String phone);
}