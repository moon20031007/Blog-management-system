package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.pojo.Article;
import com.blog.service.AccountService;
import com.blog.service.ArticleService;
import com.blog.util.currentUser.GetCurrentID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    AccountService accountService;

    @Override
    public void insert(Article article) throws Exception {
        Integer authenticatedUserId = GetCurrentID.getAuthenticatedUserId(accountService);
        article.setAuthorId(authenticatedUserId);
        articleMapper.insertSelective(article);
    }

    @Override
    public Article findByIdAndAddReads(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(Long.valueOf(id));
        if (article == null) {
            throw new NoSuchElementException();
        }
        Integer currentReadCount = article.getReadCount();
        article.setReadCount(++currentReadCount);
        articleMapper.updateByPrimaryKeySelective(article);
        return article;
    }

    @Override
    public List<Article> hot() {
        return articleMapper.hotarticles();
    }

    @Override
    public List<Article> latest() {
        return articleMapper.latestarticles();
    }
}
