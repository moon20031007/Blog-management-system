package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.blog.util.Interfaces.Likable;
import lombok.Data;

/**
 * @TableName article
 */
@Data
public class Article implements Serializable, Likable {
    private Integer articleId;

    private Integer authorId;

    private String title;

    private String content;

    private Date publishTime;

    private Integer readCount;

    private Integer likeCount;

    private Integer commentCount;

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