package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.pojo.Article;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void insert(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public Article findById(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(Long.valueOf(id));
        if (article == null) {
            throw new NoSuchElementException();
        }
        return article;
    }
}
