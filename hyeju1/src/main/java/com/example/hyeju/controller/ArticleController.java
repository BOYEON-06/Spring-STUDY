package com.example.hyeju.controller;

import com.example.hyeju.controller.entity.Article;
import com.example.hyeju.dto.ArticleForm;
import com.example.hyeju.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller

public class ArticleController {

    @Autowired //스프링 부트에 있는 기능으로 스프링부트가 미리 생성해놓은 리파지터리 객체 주입(DI)
    private ArticleRepository articleRepository; //articleRepository 객체 선언
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { //articleForm(DTO)의 form 객체를 가져옴
        log.info(form.toString());
        //System.out.println(form.toString()); // 위의 것을 출력하는 코드
        Article article = form.toEntity();//toEntity -> form객체를 엔티티 객체로 변환 역할
        log.info(article.toString());
        //System.out.println(article.toString()); //DTO가 엔티티로 잘 변환되는지 확인 출력
        Article saved = articleRepository.save(article); //article 엔티티를 저장해 saved 객체에 반환(윗 줄 article)
        log.info(saved.toString());
        //System.out.println(saved.toString()); //article이 DB(데이터베이스)에 잘 저장되는지 출력
        return "";
    }
}

