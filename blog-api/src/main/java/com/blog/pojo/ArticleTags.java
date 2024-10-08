package com.blog.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @TableName article_tags
 */
@Data
public class ArticleTags implements Serializable {
    private Integer articleId;

    private Integer tagId;

    private static final long serialVersionUID = 1L;

    public ArticleTags(Integer articleID, Integer tagID) {
        this.articleId=articleID;
        this.tagId = tagID;
    }
}