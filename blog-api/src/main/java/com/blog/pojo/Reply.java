package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName reply
 */
@Data
public class Reply implements Serializable {
    private Integer replyId;

    private Integer replierId;

    private Integer commentId;

    private String content;

    private Date replyTime;

    private Integer likeCount;

    private static final long serialVersionUID = 1L;
}