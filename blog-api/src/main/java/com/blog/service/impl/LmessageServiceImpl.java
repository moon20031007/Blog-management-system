package com.blog.service.impl;

import com.blog.mapper.LmessageMapper;
import com.blog.pojo.Lmessage;
import com.blog.service.AccountService;
import com.blog.service.LmessageService;
import com.blog.util.currentUser.GetCurrentID;
import com.blog.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void insert(Lmessage lmessage) throws Exception {
        Integer authenticatedUserId = GetCurrentID.getAuthenticatedUserId(accountService);
        lmessage.setCommenterId(authenticatedUserId);
        lmessageMapper.insertSelective(lmessage);
    }

    @Override
    public List<Lmessage> getAll() {
        return lmessageMapper.list();
    }
}
