package com.blog.controller;

import com.blog.pojo.Log;
import com.blog.service.LogService;
import com.blog.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;


    @GetMapping("/all")
    public Result all() {
        List<Log> logs = logService.all();
        return Result.success(logs);
    }
}
