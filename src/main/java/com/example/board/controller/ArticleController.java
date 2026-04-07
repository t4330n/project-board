package com.example.board.controller;

import com.example.board.domain.Article;
import com.example.board.dto.ArticleForm;
import com.example.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

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
    public String createArticle(@ModelAttribute ArticleForm form) {
//        DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(form.toString());

//        Entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/new";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id= {}", id);

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        model.addAttribute("article", article);

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        ArrayList<Article> articles = articleRepository.findAll();

        model.addAttribute("articles", articles);
        return "articles/index";
    }
}
