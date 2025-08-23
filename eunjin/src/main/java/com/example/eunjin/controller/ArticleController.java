package com.example.eunjin.controller;

import com.example.eunjin.dto.ArticleForm;
import com.example.eunjin.entity.Article;
import com.example.eunjin.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info (form.toString());
        Article article = form.toEntity();
        log.info (article.toString());
        Article saved = articleRepository.save(article);
        log.info (saved.toString());
        return "redirect:/articles/" + saved.getId(); // 리다이렉트
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info ("id= " + id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){ // 2. id를 매개변수로 받아오기 (url 주소에 있는 id를 받아오는 것이므로 PathVariable 어노테이션 사용)
                                            // 3. model 객체 받아오기 (DB에서 데이터를 가져왔으니 뷰 페이지에서 사용할 수 있도록 모델에 데이터를 등록해야 하기 때문)
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); // 1. DB에서 수정할 데이터 가져오기
        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity); // 4. articleEntity를 article로 등록
        // 뷰페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ // 매개변수로 DTO 받아오기
        log.info (form.toString());
        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity(); // DTO(form)를 엔티티(articleEntity)로 변환하기
        log.info(articleEntity.toString());
        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
                        // DB에서 데이터 찾기
        // 2-2. 기존 데이터 값을 갱신하기
        if (target != null){
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }
}
