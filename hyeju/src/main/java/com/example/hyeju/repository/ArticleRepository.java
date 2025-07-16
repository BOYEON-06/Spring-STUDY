package com.example.hyeju.repository;

import com.example.hyeju.controller.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> { //JPA에서 제공하는 인터페이스
    //id의 타입이 Long이므로 Long입력

}
