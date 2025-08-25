package com.example.boyeon.dto;

import com.example.boyeon.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Article toEntity() {
        return new Article(id, title, content); // 생성자 입력 양식에 맞게 작성
    }
}
