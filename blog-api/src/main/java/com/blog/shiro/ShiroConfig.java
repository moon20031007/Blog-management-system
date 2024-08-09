package com.blog.shiro;

import com.blog.util.encrypt.EncryptUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * ClassName: ShiroConfig
 * Package: com.blog.config
 * Description:
 *
 * @Author: Yixiao
 * @Create: 2024.8.1 - 10:47
 * @Version: v1.0
 */

@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm userRealm(){
        System.out.println("userRealm running");
        UserRealm userRealm = new UserRealm();

        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(EncryptUtil.ENCRYPT_ALGORITHM);
        matcher.setHashIterations(EncryptUtil.ITERATIONS);
        userRealm.setCredentialsMatcher(matcher);

        return userRealm;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getdefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        /* 添加Shiro的内置过滤器 不同的tag格子代表的含义
        anon： 无需认证就可以访问
        authc： 必须认证了才能让问
        user： 必须拥有 记住我 功能才能用
        perms： 拥有对某个资源的权限才能访问；
        role： 拥有某个角色权限才能访问
        例子：filterChainDefinitionMap.put("/addUser", "authc");
        现在是所有都无限制
        */
        filterChainDefinitionMap.put("article/add", "authc");
        filterChainDefinitionMap.put("/**", "anon");

        // 设置登录 URL，当用户未认证时会跳转到login页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }



}
