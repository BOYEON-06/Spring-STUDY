//DTO
package com.example.hyeju.dto;

import com.example.hyeju.controller.entity.Article;

import javax.swing.*;

public class ArticleForm {
    public Article toEntity;
    private String title;
    private String content;


    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    public Article toEntity() {
        return new Article(null,title,content);
    }
}
