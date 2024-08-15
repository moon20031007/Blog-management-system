package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.CommentMapper;
import com.blog.mapper.LikesMapper;
import com.blog.mapper.ReplyMapper;
import com.blog.pojo.Article;
import com.blog.pojo.Comment;
import com.blog.pojo.Likes;
import com.blog.pojo.Reply;
import com.blog.service.LikesService;
import com.blog.util.Enums.LikeableEnum;
import com.blog.util.Interfaces.Likable;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: LikesServiceImpl
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 14:46
 * @Version: v1.0
 */
@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public Result likeALikable(Integer userId, Integer likeableId, LikeableEnum likeableType) {
            Likable likable = null;
            Integer count = 0;

            switch (likeableType) {
                case ARTICLE:
                    likable = articleMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    count = likable.beliked();
                    articleMapper.updateByPrimaryKeySelective((Article)likable);
                    break;
                case COMMENT:
                    likable = commentMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    count = likable.beliked();
                    commentMapper.updateByPrimaryKeySelective((Comment)likable);
                    break;
                case REPLY:
                    likable = replyMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    count = likable.beliked();
                    replyMapper.updateByPrimaryKeySelective((Reply)likable);
                    break;
            }
            if (likable == null){
                return Result.error(ResultCode.LIKABLE_NOT_EXIST);
            }
            System.out.println("like: the like count is");
            System.out.println(count);

            Likes likes = new Likes(userId, likeableId, likeableType);
            if (likesMapper.insertSelective(likes) != 1){
                return Result.error(ResultCode.UPLOAD_ERROR);
            }
            return Result.success();
    }

    public Result unlikeALikable(Integer userId, Integer likeableId, LikeableEnum likeableType){
        try {

            Likable likable = null;
            Integer count = 0;

            switch (likeableType) {
                case ARTICLE:
                    likable = articleMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    count = likable.unliked();
                    articleMapper.updateByPrimaryKeySelective((Article)likable);
                    break;
                case COMMENT:
                    likable = commentMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    count = likable.unliked();
                    commentMapper.updateByPrimaryKeySelective((Comment)likable);
                    break;
                case REPLY:
                    likable = replyMapper.selectByPrimaryKey(Long.valueOf(likeableId));
                    count = likable.unliked();
                    replyMapper.updateByPrimaryKeySelective((Reply)likable);
                    break;
            }
            if (likable == null){
                return Result.error(ResultCode.LIKABLE_NOT_EXIST);
            }
            System.out.println("the like count is");
            System.out.println(count);

            Integer likeId = likesMapper.selectByIdAndType(userId, likeableId, likeableType).getLikeId();
            if (likesMapper.deleteByPrimaryKey(Long.valueOf(likeId)) != 1){
                return Result.error(ResultCode.UPLOAD_ERROR);
            }
            return Result.success();

        }catch (RuntimeException e){
            return Result.error(ResultCode.USER_NOT_EXIST);
        }catch (Exception e){
            return Result.error(ResultCode.ERROR);
        }
    }

    @Override
    public Boolean isLiking(Integer userId, Integer likeableId, LikeableEnum likeableType) {
        System.out.println(userId);
        System.out.println(likeableId);
        System.out.println(likeableType);
        Likes likeSelected = likesMapper.selectByIdAndType(userId,likeableId,likeableType);
        return likeSelected == null;
    }
}

