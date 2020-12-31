package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.wumbuk.entity.Activity;

import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /**
     * 得到状态为0和为1的活动列表，即用户和商家可以参加的活动列表
     * @return
     */
    @Select("select * from activity where status=0 or status=1")
    List<Activity> getAliveActivities();
}