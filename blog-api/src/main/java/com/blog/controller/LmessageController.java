package com.blog.controller;

import com.blog.pojo.Lmessage;
import com.blog.service.AccountService;
import com.blog.service.LmessageService;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: LmessageController
 * Package: com.blog
 * Description:
 * @Author: Yixiao
 * @Create: 2024.8.6 - 14:25
 * @Version: v1.0
 *
*/
@RestController
@CrossOrigin
@RequestMapping("/lmessage")
public class LmessageController {

    @Autowired
    private LmessageService lmessageService;

    @PostMapping("/add")
    public Result add(@RequestBody Lmessage lmessage){
        try{
            lmessageService.insert(lmessage);
            return Result.success();
        }catch (Exception e){
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/list")
    public Result<List<Lmessage>> lmessagesList(){
        return Result.success(lmessageService.getAll());
    }
}
