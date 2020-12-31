package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Sellers;

@Repository
@Mapper
public interface SellersMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Sellers record);

    int insertSelective(Sellers record);

    Sellers selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Sellers record);

    int updateByPrimaryKey(Sellers record);

    /**
     * 通过商品的id去查询商家的信息
     * @param goodid
     * @return
     */

    Sellers getSellerByGoodId(Integer goodid);
}