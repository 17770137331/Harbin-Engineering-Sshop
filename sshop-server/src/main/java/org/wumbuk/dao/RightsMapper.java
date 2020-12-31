package org.wumbuk.dao;

import org.wumbuk.entity.Rights;

public interface RightsMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Rights record);

    int insertSelective(Rights record);

    Rights selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKey(Rights record);
}