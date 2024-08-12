package com.blog.service.impl;

import com.blog.mapper.ReplyMapper;
import com.blog.pojo.Reply;
import com.blog.service.AccountService;
import com.blog.service.ReplyService;
import com.blog.util.currentUser.GetCurrentID;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.*;

/**
 * ClassName: ReplyServiceImpl
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 16:35
 * @Version: v1.0
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private AccountService accountService;

    @Override
    public Result getRepliesByComment(Integer commentID) {
        List<Reply> replies = replyMapper.selectByCommentId(Long.valueOf(commentID));

        return Result.success(replies);
    }

    @Override
    public Result insert(Reply reply){
        try {
            Integer authenticatedUserId = GetCurrentID.getAuthenticatedUserId(accountService);
            reply.setReplierId(authenticatedUserId);
            //get the parent reply if we are replying a reply, this parent reply is to comment
            if (reply.getReplyType()){
                Integer replyId = reply.getToReplyId();
                Reply replyParent = replyMapper.selectByPrimaryKey(Long.valueOf(replyId));
                if (replyParent==null){
                    throw new NoSuchElementException();
                }
                reply.setToId(replyParent.getReplierId());
                reply.setCommentId(replyParent.getCommentId());
            }
            int insert = replyMapper.insertSelective(reply);
            if (insert!=1){
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
