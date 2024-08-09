package com.blog.pojo;

import java.io.Serializable;

import com.blog.util.Enums.LikableEnum;
import lombok.Data;

/**
 * @TableName likes
 */
@Data
public class Likes implements Serializable {
    private Integer likeId;

    private Integer userId;

    private Integer likeableId;

    private LikableEnum likeableType;

    private static final long serialVersionUID = 1L;
}