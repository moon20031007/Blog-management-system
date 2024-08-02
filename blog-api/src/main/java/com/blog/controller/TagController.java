package com.blog.controller;

import com.blog.pojo.Tag;
import com.blog.service.TagService;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public Result list() {
        try {
            List<Tag> list = tagService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/detail/{id}")
    public Result get(@PathVariable Integer id) {
        try {
            Tag tag = tagService.findById(id);
            return Result.success(tag);
        } catch (NoSuchElementException e) {
            return Result.error(ResultCode.RESULE_DATA_NONE);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }
}
