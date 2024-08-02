package com.blog.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName Tag
 */
@Data
public class Tag implements Serializable {
    private Integer tagId;

    private String tagName;

    private Integer articleCount;

    private static final long serialVersionUID = 1L;
}