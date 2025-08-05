//DTO
package com.example.hyeju.dto;

import com.example.hyeju.controller.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class ArticleForm {
    public Article toEntity;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(null,title,content);
    }
}
