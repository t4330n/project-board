package com.example.board;

import com.example.board.domain.Article;
import com.example.board.repository.ArticleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitData {

    private final ArticleRepository articleRepository;

    @PostConstruct
    public void init() {
        log.info("더미 데이터 생성 시작!");

        // id는 DB가 자동 생성(IDENTITY)하므로 null을 넘겨줍니다.
        Article article1 = new Article(null, "첫 번째 글입니다", "첫 번째 더미 내용입니다.");
        Article article2 = new Article(null, "두 번째 글입니다", "두 번째 더미 내용입니다.");
        Article article3 = new Article(null, "세 번째 글입니다", "세 번째 더미 내용입니다.");

        // DB에 저장
        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        log.info("더미 데이터 생성 완료!");
    }
}
