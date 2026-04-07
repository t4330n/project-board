package com.example.board.controller;

import com.example.board.domain.Article;
import com.example.board.dto.ArticleForm;
import com.example.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public void createArticle(@ModelAttribute ArticleForm form) {
//        DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(form.toString());

//        Entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
    }
}
