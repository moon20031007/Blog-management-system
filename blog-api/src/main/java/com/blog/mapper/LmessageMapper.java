package com.blog.mapper;

import com.blog.pojo.Lmessage;
import com.blog.pojo.Tag;

import java.util.List;

/**
* @author Yixiao
* @description 针对表【lmessage】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.Lmessage
*/
public interface LmessageMapper {
    List<Lmessage> list();

    int deleteByPrimaryKey(Long id);

    int insert(Lmessage record);

    int insertSelective(Lmessage record);

    Lmessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Lmessage record);

    int updateByPrimaryKey(Lmessage record);

}
