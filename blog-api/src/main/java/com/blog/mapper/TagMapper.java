package com.blog.mapper;

import com.blog.pojo.Article;
import com.blog.pojo.Tag;

import java.util.List;

/**
* @author Yixiao
* @description 针对表【Tag】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.Tag
*/
public interface TagMapper {

    List<Tag> list();

    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Article> fetchArticle(Long id);

}
