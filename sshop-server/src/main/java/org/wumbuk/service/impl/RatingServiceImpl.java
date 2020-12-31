package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.OrderMapper;
import org.wumbuk.dao.RatingsMapper;
import org.wumbuk.entity.Order;
import org.wumbuk.entity.Ratings;
import org.wumbuk.service.RatingService;
import org.wumbuk.utils.DateToUnixTimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther PeihaoYang
 * @date 2020/12/17 - 22:13
 */
@Service("ratingService")
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingsMapper ratingsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Ratings> getRatingsByGoodid(Integer good) {
        List<Ratings> ratings = ratingsMapper.selectByGoodid(good);
        List<Ratings> temp=new ArrayList<>();
        Ratings ratings1 = new Ratings();
        for (Ratings rating : ratings) {
            ratings1=rating;
            ratings1.setUnixtime(DateToUnixTimeUtil.Date2TimeStamp(rating.getRatetime()));
            temp.add(ratings1);
        }
        return temp;
    }

    @Override
    public int makeRating(Ratings ratings) {
        System.out.println("服务层的 makeRating 函数开始执行，参数如下");
        System.out.println(ratings);

        Float score=ratings.getRatescore();
        if(score>5){
            ratings.setRatetype(1);
        }else {
            ratings.setRatetype(0);
        }
        Order order = new Order();
        order.setStatus(3);
        order.setOrderid(ratings.getOrderid());
        try {
            if(orderMapper.updateByPrimaryKeySelective(order)==0){
                return 0;
            }
        } catch (Exception e) {
            return 0;
//            e.printStackTrace();
        }

        return ratingsMapper.insertSelective(ratings);
    }
}
