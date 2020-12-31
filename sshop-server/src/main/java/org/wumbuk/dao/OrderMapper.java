package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Order;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 获得所有的订单列表,表示根据买家id
     * @return
     */
    List<Order> getOrdersAll(Integer userbuyid);

    /**
     * 获得正在进行的订单列表,表示根据买家id
     * @return
     */
    List<Order> getOrdersDoing(Integer userbuyid);

    /**
     * 获得未评价的订单列表,表示根据买家id
     * @return
     */
    List<Order> getOrdersUnrating(Integer userbuyid);

    /**
     * 获得已完成的订单列表,表示根据买家id
     * @return
     */
    List<Order> getOrdersFinished(Integer userbuyid);
}