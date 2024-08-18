package com.blog.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.blog.util.Enums.LikeableEnum;
import lombok.Data;

/**
 * @TableName likes
 */
@Data
public class Likes implements Serializable {
    private Integer likeId;

    private Integer userId;

    private Integer likeableId;

    @EnumValue()
    private LikeableEnum likeableType;

    private static final long serialVersionUID = 1L;

    public Likes() {
        likeableType= LikeableEnum.Comment;
    }

    public Likes(Integer userId, Integer likeableId, LikeableEnum likeableType) {
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
    }
}