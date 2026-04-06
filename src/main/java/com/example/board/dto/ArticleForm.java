package com.example.board.dto;

import com.example.board.domain.Article;
import lombok.Data;

@Data
public class ArticleForm {

    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
