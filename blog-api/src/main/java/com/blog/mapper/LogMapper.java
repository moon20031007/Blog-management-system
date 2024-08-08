package com.blog.mapper;

import com.blog.pojo.Log;

import java.util.List;

/**
* @author Yixiao
* @description 针对表【log】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.Log
*/
public interface LogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> all();
}
