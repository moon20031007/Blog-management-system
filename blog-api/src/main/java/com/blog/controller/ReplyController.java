package com.blog.controller;

import com.blog.pojo.Reply;
import com.blog.service.ReplyService;
import com.blog.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ReplyController
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 16:30
 * @Version: v1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/list/{id}")
    public Result GetReplies(@PathVariable Integer id){
        return replyService.getRepliesByComment(id);
    }

    @PostMapping("/add")
    public Result AddReply(@RequestBody Reply reply){
        return replyService.insert(reply);
    }
}
