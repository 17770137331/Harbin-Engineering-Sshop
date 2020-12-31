package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.GoodMapper;
import org.wumbuk.dao.OrderMapper;
import org.wumbuk.dao.RatingsMapper;
import org.wumbuk.entity.Order;
import org.wumbuk.entity.Ratings;
import org.wumbuk.service.OrderService;

import java.util.List;

/**
 * @auther PeihaoYang
 * @date 2020/12/21 - 21:00
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodMapper goodMapper;


    @Autowired
    RatingsMapper ratingsMapper;


    @Override
    public int confirmOrder(Order order) {
        System.out.println("服务层开始执行confirmOrder方法，得到的形参为");
        System.out.println(order);

        try {
            goodMapper.reduceGoodNumber(order.getNumber(),order.getGoodid());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return  orderMapper.insertSelective(order);
    }



    @Override
    public List<Order> getOrdersAll(String uid) {
        System.out.println(" getOrdersAll 执行,得到的uid为"+uid);

        return orderMapper.getOrdersAll(Integer.valueOf(uid));
    }

    @Override
    public int receiveOrder(Order order) {
        order.setStatus(2);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

//    @Override
//    public int makeRating(Ratings ratings) {
//        return ratingsMapper.insertSelective(ratings);
//    }

    @Override
    public List<Order> getOrdersDoing(String uid) {
        System.out.println(" getOrdersDoing 执行,得到的uid为"+uid);

        return orderMapper.getOrdersDoing(Integer.valueOf(uid));
    }

    @Override
    public List<Order> getOrdersUnrating(String uid) {
        System.out.println(" getOrdersUnrating 执行,得到的uid为"+uid);

        return orderMapper.getOrdersUnrating(Integer.valueOf(uid));
    }



    @Override
    public List<Order> getOrdersFinished(String uid) {
        System.out.println(" getOrdersFinished 执行,得到的uid为"+uid);
        return orderMapper.getOrdersFinished(Integer.valueOf(uid));
    }





}
