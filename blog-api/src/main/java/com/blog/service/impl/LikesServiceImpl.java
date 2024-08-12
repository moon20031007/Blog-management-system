package com.blog.service.impl;

import com.blog.mapper.CommentMapper;
import com.blog.mapper.LikesMapper;
import com.blog.mapper.ReplyMapper;
import com.blog.pojo.Likes;
import com.blog.service.AccountService;
import com.blog.service.ArticleService;
import com.blog.service.LikesService;
import com.blog.util.Enums.LikableEnum;
import com.blog.util.Interfaces.Likable;
import com.blog.util.currentUser.GetCurrentID;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.AuthenticationException;

/**
 * ClassName: LikesServiceImpl
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 14:46
 * @Version: v1.0
 */
public class LikesServiceImpl implements LikesService {

    @Autowired
    LikesMapper likesMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public Result LikeAnLikable(Likes likes) {
        try {
            Integer authenticatedUserId = GetCurrentID.getAuthenticatedUserId(accountService);
            likes.setUserId(authenticatedUserId);

            Integer likeableId = likes.getLikeableId();
            LikableEnum likeableType = likes.getLikeableType();
            Likable likable = null;

            switch (likeableType) {
                case ARTICLE:
                    likable = articleService.findByIdAndAddReads(likeableId);
                    break;
                case COMMENT:
                    likable = commentMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    break;
                case REPLY:
                    likable = replyMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    break;
            }
            if (likable == null){
                return Result.error(ResultCode.LIKABLE_NOT_EXIST);
            }
            Integer count = likable.beliked();
            System.out.println("the like count is");
            System.out.println("count");

            int inserted = likesMapper.insertSelective(likes);
            if (inserted!=1){
                return Result.error(ResultCode.UPLOAD_ERROR);
            }
            return Result.success();

        } catch (AuthenticationException e) {
            return Result.error(ResultCode.USER_NOT_LOGGED_IN);
        }catch (RuntimeException e){
            return Result.error(ResultCode.USER_NOT_EXIST);
        }catch (Exception e){
            return Result.error(ResultCode.ERROR);
        }
    }
}
