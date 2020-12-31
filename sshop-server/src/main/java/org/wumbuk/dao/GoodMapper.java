package org.wumbuk.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Good;
import java.util.List;



@Repository
@Mapper
public interface GoodMapper {
    int deleteByPrimaryKey(Integer goodid);

    int insert(Good record);

    int insertSelective(Good record);

    Good selectByPrimaryKey(Integer goodid);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);


    /**
     * 得到商品列表
     * @return
     */
    List<Good> getGoods();

    /**
     * 更新商品的浏览量加1
     * @param goodid
     * @return
     */
    @Update("update good set likes=likes+1 where goodid=#{goodid}")
    int addLikes(Integer goodid);


    /**
     * 企图对数据库进行模糊查询，但是一定要记得对字符串进行预处理，即前后拼接%字符
     * @param keyword
     * @return
     */
    List<Good> getGoodsVague(String keyword);


    @Update("update good set good.number=good.number-#{number} where good.goodid=#{goodid}")
    void reduceGoodNumber(Integer number,Integer goodid);
}