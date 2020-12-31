package org.wumbuk.dao;

import org.wumbuk.entity.GoodRights;

public interface GoodRightsMapper {
    int insert(GoodRights record);

    int insertSelective(GoodRights record);
}