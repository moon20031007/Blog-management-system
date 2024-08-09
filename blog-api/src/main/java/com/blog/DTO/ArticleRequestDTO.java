package com.blog.DTO;

import com.blog.pojo.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleRequestDTO {
    private Article article;
    private List<Integer> tagIDs;
}