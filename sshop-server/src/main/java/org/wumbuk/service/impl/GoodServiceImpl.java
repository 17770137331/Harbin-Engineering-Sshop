package org.wumbuk.service.impl;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.ActivitySellersMapper;
import org.wumbuk.dao.GoodMapper;
import org.wumbuk.dao.ImagesMapper;
import org.wumbuk.dao.SellersMapper;
import org.wumbuk.entity.*;
import org.wumbuk.service.GoodService;
import org.wumbuk.utils.JsonUtilList;
import org.wumbuk.utils.JsonUtils;
import org.wumbuk.utils.TimeStamp;

import java.util.*;

/**
 * @auther PeihaoYang
 * @date 2020/12/8 - 22:52
 */
@Service("goodService")
public class GoodServiceImpl implements GoodService {
    @Autowired
    GoodMapper goodMapper;
    
    @Autowired
    SellersMapper sellersMapper;
    @Autowired
    ImagesMapper imagesMapper;

    @Autowired
    ActivitySellersMapper activitySellersMapper;


    @Override
    public List<Good> getGoods() {
        return goodMapper.getGoods();
    }

    @Override
    public Good getGoodByGoodId(Integer goodid,int type) {
        if(type==1) {
            int addlike=goodMapper.addLikes(goodid);
            return goodMapper.selectByPrimaryKey(goodid);
        }
        return null;
    }

    @Override
    public Sellers getSeller(Good good) {
        return sellersMapper.getSellerByGoodId(good.getGoodid());
    }

    @Override
    public List<Good> getGoodsVague(String keyword) {
        //我事先进行字符串的预处理了，便于直接进行模糊查询
        keyword="%"+keyword+"%";
        System.out.println("服务层函数得到的参数为"+keyword);
        return goodMapper.getGoodsVague(keyword);
    }

    @Override
    public int putGood(Map<String, Object> map, Integer uid) {
        Good good = new Good();
        JsonUtilList jsonUtilList = new JsonUtilList<>();
        List<ImgCOSEntity> otherlinks = jsonUtilList.mapToList(map, ImgCOSEntity.class, "otherlinks");
        List<Integer> checkList = jsonUtilList.mapToList(map, Integer.class,"checkList");
        good.setTitle(map.get("title").toString());
        good.setNumber(Integer.valueOf(map.get("number").toString()));
        good.setPrice(Float.valueOf(map.get("price").toString()));
        good.setComment(map.get("comment").toString());
        good.setSid(uid);
        good.setGoodid(Integer.valueOf(TimeStamp.getTimeStamp("6").substring(6,10)));
        good.setCid(Integer.valueOf(map.get("cid").toString()));
        good.setLikes(0);
        good.setScore(Float.valueOf("60"));
        good.setSellnumber(0);
        if(!otherlinks.isEmpty()) {
            good.setImglink(otherlinks.get(0).getUrl());
        }
        System.out.println("服务层得到的good为"+good);
        System.out.println("服务层得到的 otherlinks 为"+otherlinks);
        System.out.println("服务层得到的 checkList 为"+checkList);
        int i = goodMapper.insertSelective(good);
        System.out.println("服务层得到的i为"+i);
       Boolean checkListflag=true;

        for (Integer integer : checkList) {
            ActivitySellers activitySellers = new ActivitySellers();
            activitySellers.setAid(integer);
            activitySellers.setUserid(good.getSid());
            System.out.println("得到的activitySellers为"+activitySellers);
            int i1 = activitySellersMapper.insert(activitySellers);
            if(i1!=1){
                System.out.println("得到活动记录插入失败！！！");
                checkListflag=false;
                break;
            }
        }
        Boolean otherlinkflag=true;
        for (ImgCOSEntity otherlink : otherlinks) {
            Images images = new Images();
            images.setType(0);
            images.setReferid(good.getGoodid());
            images.setLink(otherlink.getUrl());
            int i1 = imagesMapper.insertSelective(images);
            if (i1 != 1) {
                otherlinkflag = false;
                break;
            }
        }
            if(i==0||!otherlinkflag||!checkListflag){
                System.out.println("服务层发布商品失败");
                System.out.println("i:"+i);
                System.out.println("otherlinkflag:"+otherlinkflag);
                System.out.println("checkListflag:"+checkListflag);
                return -1;
            }
            return  0;
    }











}
