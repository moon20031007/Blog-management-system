package com.blog.controller;

import com.blog.pojo.Likes;
import com.blog.service.AccountService;
import com.blog.service.LikesService;
import com.blog.util.Enums.LikableEnum;
import com.blog.util.currentUser.GetCurrentID;
import com.blog.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: LikeController
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 11:51
 * @Version: v1.0
 */
public class LikesController {


    @Autowired
    LikesService likesService;

    public Result likeAnArticle(Likes like) throws Exception{
        return likesService.LikeAnLikable(like);
    }

    
}
