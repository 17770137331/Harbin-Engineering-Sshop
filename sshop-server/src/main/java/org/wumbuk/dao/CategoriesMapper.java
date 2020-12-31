package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Categories;

import java.util.List;

@Repository
@Mapper
public interface CategoriesMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Categories record);

    int insertSelective(Categories record);

    Categories selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);

    @Select("select * from categories")
    List<Categories> getCategories();
}