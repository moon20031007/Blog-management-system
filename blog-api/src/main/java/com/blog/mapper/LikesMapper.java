package com.blog.mapper;

import com.blog.pojo.Likes;

/**
* @author Yixiao
* @description 针对表【likes】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.Likes
*/
public interface LikesMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);

}
