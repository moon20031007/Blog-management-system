package com.blog.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName likes
 */
@Data
public class Likes implements Serializable {
    private Integer likeId;

    private Integer userId;

    private Integer likeableId;

    private Object likeableType;

    private static final long serialVersionUID = 1L;
}