package com.blog.service.impl;

import com.blog.mapper.LmessageMapper;
import com.blog.pojo.Lmessage;
import com.blog.service.AccountService;
import com.blog.service.LmessageService;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * ClassName: LmessageServiceImpl
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 15:18
 * @Version: v1.0
 */
@Service
public class LmessageServiceImpl implements LmessageService {

    @Autowired
    LmessageMapper lmessageMapper;

    @Autowired
    AccountService accountService;

    @Override
    public Result insert(Lmessage lmessage, Subject currentUser) {

        String username = (String) currentUser.getPrincipal();
        Integer id = accountService.getIDByUsername(username);

        if (id==null){
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        lmessage.setCommenterId(id);

        int insert = lmessageMapper.insertSelective(lmessage);
        if (insert!=1){
            return Result.error(ResultCode.UPLOAD_ERROR);
        }
        return Result.success();
    }

    @Override
    public List<Lmessage> getAll() {
        return lmessageMapper.list();
    }
}
