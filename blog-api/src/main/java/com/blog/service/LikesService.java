package com.blog.service;

import com.blog.util.Enums.LikeableEnum;
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
    Result likeALikable(Integer userId, Integer likableId, LikeableEnum likableType);
    public Result unlikeALikable(Integer userId, Integer likableId, LikeableEnum likableType);
    Boolean isLiking(Integer userId, Integer likableId, LikeableEnum likableType);

}
