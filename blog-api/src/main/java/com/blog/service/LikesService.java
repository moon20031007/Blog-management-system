package com.blog.service;

import com.blog.pojo.Likes;
import com.blog.util.result.Result;

/**
 * ClassName: LikesService
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 14:45
 * @Version: v1.0
 */
public interface LikesService {
    Result LikeAnLikable(Likes likes);
}
