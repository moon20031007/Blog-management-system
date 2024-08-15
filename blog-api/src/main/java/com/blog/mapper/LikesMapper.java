package com.blog.mapper;

import com.blog.pojo.Likes;
import com.blog.util.Enums.LikeableEnum;

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

    Likes selectByIdAndType(Integer userId, Integer likeableId, LikeableEnum likeableType);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);

}
