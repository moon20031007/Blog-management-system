package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName log
 */
@Data
public class Log implements Serializable {
    private Integer logId;

    private String content;

    private Date time;

    private static final long serialVersionUID = 1L;
}