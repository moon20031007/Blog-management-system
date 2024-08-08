package com.blog.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ArticleTagsService
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 15:20
 * @Version: v1.0
 */
public interface ArticleTagsService {
    void saveArticleTags(Integer articleID, List<Integer>tagsID);
}