package com.blog.controller;

import com.blog.pojo.Article;
import com.blog.service.ArticleService;
import com.blog.util.result.Result;
import com.blog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/detail/{id}")
    public Result get(@PathVariable Integer id) {
        try {
            Article article =  articleService.findById(id);
            return Result.success(article);
        } catch (NoSuchElementException e) {
            return Result.error(ResultCode.RESULE_DATA_NONE);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Article article) {
        try {
            articleService.insert(article);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/hot")
    public Result hot() {
        try {
            List<Article> hotArticles = articleService.hot();
            return Result.success(hotArticles);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/latest")
    public Result latest() {
        try {
            List<Article> latestArticles = articleService.latest();
            return Result.success(latestArticles);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR);
        }
    }
}
