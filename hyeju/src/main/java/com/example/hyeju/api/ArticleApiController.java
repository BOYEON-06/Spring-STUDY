package com.example.hyeju.api;

import com.example.hyeju.controller.entity.Article;
import com.example.hyeju.dto.ArticleForm;
import com.example.hyeju.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    //GET(데이터 조회)
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    //POST(데이터 생성)
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){ //create() 메서드 정의
        Article article = dto.toEntity(); //dto를 엔티티로 변환해 article변수에 저장
        return articleRepository.save(article);//articleRe 통해 데베에 저장
    }
    //PATCH(데이터 수정)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        //1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}",id,article.toString());
        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if (target == null || article.getId() != id){
            //400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {},article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target); //수정한 article 엔티티를 DB에 저장(기존 데이터에 새 데이터 넣음)
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE(데이터 삭제)
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> Delete(@PathVariable Long id){
        //1.삭제 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2.잘못된 요청 처리하기
        if (target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        //3.대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
