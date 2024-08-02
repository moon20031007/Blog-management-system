package com.blog.mapper;

import com.blog.pojo.ArticleTags;

/**
* @author Yixiao
* @description 针对表【article_tags】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.ArticleTags
*/
public interface ArticleTagsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ArticleTags record);

    int insertSelective(ArticleTags record);

    ArticleTags selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleTags record);

    int updateByPrimaryKey(ArticleTags record);

}
