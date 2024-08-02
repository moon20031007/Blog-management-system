package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName lmessage
 */
@Data
public class Lmessage implements Serializable {
    private Integer lmessageId;

    private Integer commenterId;

    private Date leaveTime;

    private Integer likeCount;

    private static final long serialVersionUID = 1L;
}