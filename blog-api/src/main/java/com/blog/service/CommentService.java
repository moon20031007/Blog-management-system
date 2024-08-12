package com.blog.service;

import com.blog.pojo.Comment;
import com.blog.pojo.Reply;
import com.blog.util.result.Result;

/**
 * ClassName: CommentService
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 16:05
 * @Version: v1.0
 */
public interface CommentService {
    public Result getCommentsByArticle(Integer articleID);

    public Result insert(Comment comment) ;
}

