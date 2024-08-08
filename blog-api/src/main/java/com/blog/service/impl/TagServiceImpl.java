package com.blog.service.impl;

import com.blog.mapper.TagMapper;
import com.blog.pojo.Article;
import com.blog.pojo.Tag;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> list() {
        return tagMapper.list();
    }

    @Override
    public Tag findById(Integer id) {
        Tag tag = tagMapper.selectByPrimaryKey(Long.valueOf(id));
        if (tag == null) {
            throw new NoSuchElementException("No such tag");
        }
        return tag;
    }

    @Override
    public List<Article> fetchArticles(Integer id) {
        return tagMapper.fetchArticle(Long.valueOf(id));
    }

    @Override
    public List<Tag> hot() {
        return tagMapper.hottags();
    }
}
