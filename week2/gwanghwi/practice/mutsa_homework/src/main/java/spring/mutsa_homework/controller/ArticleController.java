package spring.mutsa_homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mutsa_homework.entity.Article;
import spring.mutsa_homework.service.ArticleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 생성
    @PostMapping("/api/v1/articles")
    public ResponseEntity<Long> createArticle(@RequestBody Article article) {
        return ResponseEntity.status(201).body(articleService.createArticle(article));
    }

    // 단건 조회
    @GetMapping("/api/v1/article/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleService.findById(articleId));
    }

    // 전체 조회
    @GetMapping("/api/v1/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.findAll());
    }

    // 제목으로 검색
    @GetMapping("/api/v1/articles/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String title) {
        return ResponseEntity.ok(articleService.findByTitle(title));
    }
}
