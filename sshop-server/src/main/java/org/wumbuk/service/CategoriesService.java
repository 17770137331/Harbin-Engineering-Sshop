package org.wumbuk.service;

import org.wumbuk.entity.Categories;
import org.wumbuk.entity.User;

import java.util.List;

/**
 * @auther PeihaoYang
 * @date 2020/12/8 - 20:09
 */

public interface CategoriesService {

    /**
     * 得到主页的商品类别列表
     * @return
     */
    List<Categories> getCategories();
}
