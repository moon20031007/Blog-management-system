package com.blog.mapper;

import com.blog.pojo.Reply;

/**
* @author Yixiao
* @description 针对表【reply】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.Reply
*/
public interface ReplyMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

}
