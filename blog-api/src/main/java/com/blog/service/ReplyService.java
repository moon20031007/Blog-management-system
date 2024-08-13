package com.blog.service;

import com.blog.pojo.Reply;
import com.blog.util.result.Result;

/**
 * ClassName: ReplyService
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 16:34
 * @Version: v1.0
 */
public interface ReplyService {
    public Result getRepliesByArticleId(Integer commentID);

    public Result insert(Reply reply) ;
}
