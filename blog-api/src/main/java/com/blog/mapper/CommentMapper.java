package com.blog.mapper;

import com.blog.pojo.Comment;
import com.blog.pojo.Reply;

import java.util.List;

/**
* @author Yixiao
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.Comment
*/
public interface CommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByArticleId(Long id);

}
