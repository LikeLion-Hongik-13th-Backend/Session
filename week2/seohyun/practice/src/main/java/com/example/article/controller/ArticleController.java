package com.example.article.controller;

import com.example.article.domain.Article;
import com.example.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/api/v1/articles")
    public ResponseEntity<Long> createArticle(@RequestBody Article article){
        return ResponseEntity.status(201).body(articleService.createArticle(article));
    }

    @GetMapping("/api/v1/article/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleService.findById(articleId));
    }

    @GetMapping("/api/v1/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/api/v1/articles/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String title) {
        return ResponseEntity.ok(articleService.findByTitle(title));
    }
}
