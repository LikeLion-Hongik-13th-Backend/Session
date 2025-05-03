package com.example.mutsa_practice2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.example.mutsa_practice2.entity.Article;
import com.example.mutsa_practice2.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/api/v1/articles")
    public ResponseEntity<Long> createArticle(@RequestBody Article article) {
        return ResponseEntity.status(201).body(articleService.createArticle(article));
    }

    @GetMapping("/api/v1/article/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable Long articleId){
        return ResponseEntity.ok(articleService.findById(articleId));
    }

    @GetMapping("/api/v1/articles")
    public ResponseEntity<List<Article>> getAllArticles(){
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/articles/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String title) {
        return ResponseEntity.ok(articleService.findByTitle(title));
    }
}
