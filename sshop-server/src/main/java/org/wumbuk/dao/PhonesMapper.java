package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Phones;

@Repository
@Mapper
public interface PhonesMapper {
    int insert(Phones record);

    int insertSelective(Phones record);
}