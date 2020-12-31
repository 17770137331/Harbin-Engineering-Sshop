package org.wumbuk.service;

import org.omg.CORBA.ORB;
import org.wumbuk.entity.Order;

import java.util.List;

/**
 * @auther PeihaoYang
 * @date 2020/12/21 - 20:59
 */

public interface OrderService {


    /**
     * 用来表示用户的开始发订单,返回1表示是创建订单成功！，返回-1表示创建订单失败
     * @param order
     * @return
     */
    int confirmOrder(Order order);


    /**
     * 查询所有完成的订单
     * @param uid
     * @return
     */
    List<Order> getOrdersFinished(String uid);

    /**
     * 查询所有未评论的订单
     * @param uid
     * @return
     */
    List<Order> getOrdersUnrating(String uid);

    /**
     * 查询所有正在进行的订单
     * @param uid
     * @return
     */
    List<Order> getOrdersDoing(String uid);

    /**
     * 查询所有的订单
     * @param uid
     * @return
     */
    List<Order> getOrdersAll(String uid);


    /**
     *表示确认收货
     * @param order
     * @return
     */
    int receiveOrder(Order order);


}
