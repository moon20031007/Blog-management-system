package com.blog.controller;

import com.blog.pojo.Comment;
import com.blog.pojo.Reply;
import com.blog.service.CommentService;
import com.blog.service.ReplyService;
import com.blog.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CommentController
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 15:51
 * @Version: v1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/list/{id}")
    public Result GetReplies(@PathVariable Integer id){
        return commentService.getCommentsByArticle(id);
    }

    @PostMapping("/add")
    public Result AddReply(@RequestBody Comment comment){
        return commentService.insert(comment);
    }
}
