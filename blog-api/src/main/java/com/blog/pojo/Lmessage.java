package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.blog.util.Interfaces.Likable;
import lombok.Data;

/**
 * @TableName lmessage
 */
@Data
public class Lmessage implements Serializable, Likable {
    private Integer lmessageId;

    private Integer commenterId;

    private Date leaveTime;

    private Integer likeCount;

    private String content;

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