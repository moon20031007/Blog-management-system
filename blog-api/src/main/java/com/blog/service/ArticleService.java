package com.blog.service;

import com.blog.pojo.Article;

public interface ArticleService {

    void insert(Article article);

    Article findById(Integer id);
}
