package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName account
 */
@Data
public class Account implements Serializable {
    private Integer accountId;

    private String username;

    private String password;

    private String nickname;

    private Object gender;

    private Date birthday;

    private String phone;

    private String address;

    private String email;

    private static final long serialVersionUID = 1L;
}