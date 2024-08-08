package com.blog.service;

import com.blog.pojo.Article;

import java.util.List;

public interface ArticleService {

    void insert(Article article) throws Exception;

    Article findById(Integer id);

    List<Article> hot();

    List<Article> latest();
}
