package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.ActivitySellers;

@Mapper   //声明是一个Mapper，与springbootApplication中的MapperScan二选一写上即可
@Repository
public interface ActivitySellersMapper {
    int insert(ActivitySellers record);

    int insertSelective(ActivitySellers record);
}