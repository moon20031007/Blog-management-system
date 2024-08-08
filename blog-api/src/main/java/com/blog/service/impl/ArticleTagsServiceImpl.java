package com.blog.service.impl;

import com.blog.mapper.ArticleTagsMapper;
import com.blog.mapper.TagMapper;
import com.blog.pojo.ArticleTags;
import com.blog.pojo.Tag;
import com.blog.service.ArticleTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ArticleTagsServiceImpl
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 15:20
 * @Version: v1.0
 */
@Service
public class ArticleTagsServiceImpl implements ArticleTagsService {
    @Autowired
    ArticleTagsMapper articleTagsMapper;
//    @Autowired
//    TagMapper tagMapper;
    @Override
    public void saveArticleTags(Integer articleID, List<Integer> tagsID) {

//        需要有对应的tag在tag表里才能加
//        Tag tag = new Tag();
//        tag.setTagName("java");
//        tag.setArticleCount(0);
//        Tag tag1 = new Tag();
//        tag1.setTagName("scala");
//        tag1.setArticleCount(0);
//        Tag tag2 = new Tag();
//        tag2.setTagName("c");
//        tag2.setArticleCount(0);
//
//        tagMapper.insert(tag);
//        tagMapper.insert(tag1);
//        tagMapper.insert(tag2);

        tagsID.forEach( tagID->
                articleTagsMapper.insertSelective(new ArticleTags(articleID,tagID))
        );
    }
}