package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Ratings;

import java.util.List;

@Mapper
@Repository
public interface RatingsMapper {
    int deleteByPrimaryKey(Integer ratingid);

    int insert(Ratings record);

    int insertSelective(Ratings record);

    Ratings selectByPrimaryKey(Integer ratingid);

    int updateByPrimaryKeySelective(Ratings record);

    int updateByPrimaryKey(Ratings record);

    List<Ratings> selectByGoodid(Integer goodid);

}