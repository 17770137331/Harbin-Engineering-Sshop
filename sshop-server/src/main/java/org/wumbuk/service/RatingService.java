package org.wumbuk.service;

import org.wumbuk.entity.Ratings;

import java.util.List;

/**
 * @auther PeihaoYang
 * @date 2020/12/17 - 22:12
 */

public interface RatingService {

    List<Ratings> getRatingsByGoodid(Integer goodid);

    /**
     * 代表评价的功能函数，其中ratings没有分数的好坏
     * @param ratings
     * @return
     */
    int makeRating(Ratings ratings);
}
