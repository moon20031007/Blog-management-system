package com.blog.service.impl;

import com.blog.mapper.CommentMapper;
import com.blog.pojo.Comment;
import com.blog.pojo.Reply;
import com.blog.service.AccountService;
import com.blog.service.CommentService;
import com.blog.util.currentUser.GetCurrentID;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ClassName: CommentServiceImpl
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 16:07
 * @Version: v1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private AccountService accountService;

    @Override
    public Result getCommentsByArticle(Integer articleID) {
        List<Comment> comments = commentMapper.selectByArticleId(Long.valueOf(articleID));

        return Result.success(comments);
    }

    @Override
    public Result insert(Comment comment){
        try {
            Integer authenticatedUserId = GetCurrentID.getAuthenticatedUserId(accountService);
            comment.setCommenterId(authenticatedUserId);
            if (commentMapper.insertSelective(comment) != 1){
                throw new UnsupportedOperationException();
            }
            return Result.success();
        }catch (AuthenticationException e) {
            return Result.error(ResultCode.USER_NOT_LOGGED_IN);
        }catch (NoSuchElementException e){
            return Result.error(ResultCode.USER_NOT_EXIST);
        }catch (UnsupportedOperationException e ){
            return Result.error(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
        } catch (Exception e){
            return Result.error(ResultCode.ERROR);
        }


    }
}
