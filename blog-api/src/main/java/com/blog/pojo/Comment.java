package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName comment
 */
@Data
public class Comment implements Serializable {
    private Integer commentId;

    private Integer commenterId;

    private Integer articleId;

    private String content;

    private Date commentTime;

    private Integer likeCount;

    private static final long serialVersionUID = 1L;
}