package com.blog.controller;

import com.blog.pojo.Likes;
import com.blog.service.AccountService;
import com.blog.service.LikesService;
import com.blog.util.Enums.LikeableEnum;
import com.blog.util.currentUser.GetCurrentID;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * ClassName: LikeController
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 11:51
 * @Version: v1.0
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/like")
public class LikesController {


    @Autowired
    LikesService likesService;
    @Autowired
    AccountService accountService;

    @PutMapping("/{likeableEnum}/{likeableId}")
    public Result likeOrUnlikeALikable(@PathVariable LikeableEnum likeableEnum, @PathVariable Integer likeableId) throws Exception{
        try {
            Integer userId = GetCurrentID.getAuthenticatedUserId(accountService);
            Likes likes = new Likes(userId, likeableId, likeableEnum);
            if (!likesService.isLiking(userId, likeableId, likeableEnum)){
                System.out.println("like it");
                return likesService.likeALikable(userId, likeableId, likeableEnum);
            } else {
                System.out.println("unlike it");
                return likesService.unlikeALikable(userId, likeableId, likeableEnum);
            }
        } catch (AuthenticationException e) {
            return Result.error(ResultCode.USER_NOT_LOGGED_IN);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/{likeableType}/{likeableId}")
    public Result getLikableCount(@PathVariable LikeableEnum likeableType, @PathVariable Integer likeableId) throws Exception{
        try {
            Integer userId = GetCurrentID.getAuthenticatedUserId(accountService);
            return Result.success(likesService.isLiking(userId,likeableId, likeableType));
        } catch (AuthenticationException e) {
            return Result.error(ResultCode.USER_NOT_LOGGED_IN);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }
}
