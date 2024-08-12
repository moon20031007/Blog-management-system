package com.blog.util.currentUser;

import com.blog.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

import java.util.NoSuchElementException;

/**
 * ClassName: getCurrentuserID
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 11:20
 * @Version: v1.0
 */
public class GetCurrentID {
    public static Integer getAuthenticatedUserId(AccountService accountService) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            throw  new AuthenticationException();
        }
        String username = (String) currentUser.getPrincipal();
        Integer id = accountService.getIDByUsername(username);
        if (id == null) {
            throw new NoSuchElementException("user not found");
        }
        return id;
    }
}
