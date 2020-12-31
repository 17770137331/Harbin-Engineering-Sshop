package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.CategoriesMapper;
import org.wumbuk.entity.Categories;
import org.wumbuk.entity.User;
import org.wumbuk.service.CategoriesService;

import java.util.List;

/**
 * @auther PeihaoYang
 * @date 2020/12/8 - 20:11
 */

@Service("CategoriesService")
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesMapper categoriesMapper;

    @Override
    public List<Categories> getCategories() {
        return categoriesMapper.getCategories();
    }
}
