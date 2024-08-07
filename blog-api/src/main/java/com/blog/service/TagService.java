package com.blog.service;

import com.blog.pojo.Article;
import com.blog.pojo.Tag;

import java.util.List;

public interface TagService {

    List<Tag> list();

    Tag findById(Integer id);

    List<Article> fetchArticles(Integer id);

    List<Tag> hot();
}
