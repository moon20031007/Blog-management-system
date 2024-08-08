package com.blog.service;


import com.blog.pojo.Account;
import com.blog.util.result.Result;
import org.yaml.snakeyaml.events.Event;


public interface AccountService {

    void insert(Account account);

    Account getAccountByUsername(String username);

    Integer getIDByUsername(String username);

    Account defaultAccount();

    Result delete(Account account);

    Account getAccountByID(Integer id);
}
