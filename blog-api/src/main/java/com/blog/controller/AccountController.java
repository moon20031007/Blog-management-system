package com.blog.controller;


import com.blog.pojo.Account;
import com.blog.service.AccountService;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/add")
    public Result add(@RequestBody Account account) {
        try {
            accountService.insert(account);
            System.out.println("新增用户: " + account.getUsername());
            return Result.success();
        } catch (DuplicateKeyException e) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/delete/{username}")
    public Result delete(@PathVariable("username") String username) {

        Account accountByUsername = accountService.getAccountByUsername(username);

        if (null == accountByUsername){
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        System.out.println("删除用户: " + username);
        return accountService.delete(accountByUsername);
    }

    @GetMapping("/get/{username}")
    public Result getAccount(@PathVariable("username") String username){

        Account accountByUsername = accountService.getAccountByUsername(username);

        if (null == accountByUsername){
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        return Result.success();
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Integer id) {
        try {
            Account account = accountService.getAccountByID(id);
            return Result.success(account);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/names")
    public Result names(@RequestParam List<Integer> userIds) {
        List<Account> accounts = accountService.getNames(userIds);
        return Result.success(accounts);
    }
}
