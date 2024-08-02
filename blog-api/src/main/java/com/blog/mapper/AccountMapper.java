package com.blog.mapper;

import com.blog.pojo.Account;
import org.apache.ibatis.annotations.Mapper;


/**
* @author Yixiao
* @description 针对表【account】的数据库操作Mapper
* @createDate 2024-07-30 15:16:50
* @Entity com.blog.pojo.Account
*/
@Mapper
public interface AccountMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    Account selectByUsername(String username);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

}
