package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.blog.util.Interfaces.Likable;
import lombok.Data;

/**
 * @TableName reply
 */
@Data
public class Reply implements Serializable, Likable {
    private Integer replyId;

    private Integer replierId;

    private Integer commentId;

    private String content;

    private Date replyTime;

    private Integer likeCount;

    private static final long serialVersionUID = 1L;

    @Override
    public Integer unliked() {
        return --likeCount;
    }
    @Override
    public Integer beliked() {
        return ++likeCount;
    }
}