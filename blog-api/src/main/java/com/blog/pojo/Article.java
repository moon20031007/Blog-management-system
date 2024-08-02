package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName article
 */
@Data
public class Article implements Serializable {
    private Integer articleId;

    private Integer authorId;

    private String content;

    private Date publishTime;

    private Integer readCount;

    private Integer likeCount;

    private Integer commentCount;

    private static final long serialVersionUID = 1L;
}