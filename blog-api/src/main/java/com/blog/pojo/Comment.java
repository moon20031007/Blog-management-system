package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.blog.util.Interfaces.Likable;
import lombok.Data;

/**
 * @TableName comment
 */
@Data
public class Comment implements Serializable, Likable {
    private Integer commentId;

    private Integer commenterId;

    private Integer articleId;

    private String content;

    private Date commentTime;

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