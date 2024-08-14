package com.blog.service.impl;

import com.blog.mapper.AccountMapper;
import com.blog.pojo.Account;
import com.blog.service.AccountService;
import com.blog.util.encrypt.EncryptUtil;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void insert(Account account) {
        if (accountMapper.selectByUsername(account.getUsername()) != null) {
            throw new DuplicateKeyException("Username already exists");
        }
        SimpleHash hash = new SimpleHash(
                EncryptUtil.ENCRYPT_ALGORITHM,
                account.getPassword(),
                EncryptUtil.SALT,
                EncryptUtil.ITERATIONS);
        account.setPassword(hash.toHex());
        accountMapper.insert(account);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountMapper.selectByUsername(username);
    }

    public Account defaultAccount(){
        Account account1 = new Account();
        account1.setUsername("blog");
        account1.setPassword("123456");
        account1.setNickname("bg");
        account1.setGender("女");
        account1.setBirthday(new Date(2024,8,1) );
        account1.setPhone("12388886666");
        account1.setAddress("insta360");
        account1.setEmail("88886666@qq.com");
        return account1;
//        {
//            "username":"log",
//                "password":123456,
//                "nickname":"bg",
//                "gender":"男",
//                "birthday":"2024-08-02T09:42:46.000Z",
//                "phone":"88886666",
//                "address":"insta360",
//                "email":"88886666@qq.com"
//        }
    }

    @Override
    public Integer getIDByUsername(String username) {
        Account accountByUsername = getAccountByUsername(username);
        if (accountByUsername == null) {
            return null;
        }
        Integer accountId = accountByUsername.getAccountId();
        return accountId;
    }

    @Override
    public Result delete(Account account) {
        Long accountId = Long.valueOf(account.getAccountId());
        if (accountMapper.deleteByPrimaryKey(accountId) != 1){
            System.out.println("delete mapper fail");
            return Result.error(ResultCode.ERROR);
        }
        return Result.success();
    }

    @Override
    public Account getAccountByID(Integer id) {
        return accountMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @Override
    public List<Account> getNames(List<Integer> ids) {
        return accountMapper.getNames(ids);
    }
}
