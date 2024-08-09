package com.blog.shiro;

import com.blog.controller.LoginController;
import com.blog.pojo.Account;
import com.blog.service.AccountService;
import com.blog.util.encrypt.EncryptUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: UserRealm
 * Package: com.blog.shiro
 * Description:
 *
 * @Author: Yixiao
 * @Create: 2024.8.1 - 10:53
 * @Version: v1.0
 */

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginController loginController;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        Account account = accountService.getAccountByUsername(username);
        if (account == null) {
           throw new UnknownAccountException();//没找到帐号
       }

        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                account.getUsername(),
                account.getPassword(),
                ByteSource.Util.bytes(EncryptUtil.SALT),
                getName());

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了doGetAuthorizationInfo");
        return null;
    }

}
