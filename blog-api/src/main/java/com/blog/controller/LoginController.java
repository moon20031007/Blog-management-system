package com.blog.controller;

import com.blog.pojo.Account;
import com.blog.service.AccountService;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: LoginController
 * Package: com.blog.controller
 * Description:
 *
 * @Author: Yixiao
 * @Create: 2024.8.1 - 14:14
 * @Version: v1.0
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public Result login(@RequestBody Account account){
        String username = account.getUsername();
        String password = account.getPassword();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

        try {
            subject.login(usernamePasswordToken);
            System.out.println("登陆成功，用户名为"+username);
            return Result.success();
        } catch (UnknownAccountException e) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        } catch (LockedAccountException e) {
            return Result.error(ResultCode.USER_ACCOUNT_FORBIDDEN);
        } catch (AuthenticationException e) {
            return Result.error(ResultCode.USER_LOGIN_ERROR);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        String username = account.getUsername();

        if (accountService.getAccountByUsername(username) != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }
        accountService.insert(account);
        System.out.println("注册成功，用户名为："+username);
        return Result.success();
    }
    @GetMapping("/logout")
    public Result logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        System.out.println("退出成功");

        return Result.success();
    }
}
