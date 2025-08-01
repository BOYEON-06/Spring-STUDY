package com.example.eunjin.controller;

import com.example.eunjin.dto.ArticleForm;
import com.example.eunjin.entity.Article;
import com.example.eunjin.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입 : 의존성 주입
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 폼 데이터를 DTO로 받기
        log.info (form.toString());
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info (article.toString());
        //System.out.println(article.toString()); // DTO가 에닡티로 잘 변환되는지 확인 출력
        // 2. 리포지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info (saved.toString());
        //System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 확인 출력
        return "";
    }
}
