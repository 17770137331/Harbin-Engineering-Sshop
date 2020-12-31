package org.wumbuk.dao;

import org.wumbuk.entity.GoodPhotos;

public interface GoodPhotosMapper {
    int deleteByPrimaryKey(Integer imgid);

    int insert(GoodPhotos record);

    int insertSelective(GoodPhotos record);

    GoodPhotos selectByPrimaryKey(Integer imgid);

    int updateByPrimaryKeySelective(GoodPhotos record);

    int updateByPrimaryKey(GoodPhotos record);
}