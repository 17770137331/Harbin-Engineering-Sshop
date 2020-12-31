package org.wumbuk.service;

import org.wumbuk.entity.Good;
import org.wumbuk.entity.Sellers;

import java.util.List;
import java.util.Map;

/**
 * @auther PeihaoYang
 * @date 2020/12/8 - 22:50
 */

public interface GoodService {


    List<Good> getGoods();

    /**
     * 通过商品的id去查询某一个商品的具体信息，
     * type为一表示是用户此时正常浏览商品的具体信息，需要我们将浏览量加1
     *
     * @param goodid
     * @param type
     * @return
     */
    Good getGoodByGoodId(Integer goodid,int type);

    /**
     * 通过goodid去查询商家的信息
     * @param good
     * @return
     */

    Sellers getSeller(Good good);

    /**
     * 通过关键字查询商品的列表
     *
     * @return
     */
    List<Good> getGoodsVague(String keyword);

    /**
     * 发布产品，其中map的字段有title,price,number,comment,checkList,cid,
     * otherlinks
     * @param map
     * @return
     */
    int putGood(Map<String, Object> map,Integer uid);
}
