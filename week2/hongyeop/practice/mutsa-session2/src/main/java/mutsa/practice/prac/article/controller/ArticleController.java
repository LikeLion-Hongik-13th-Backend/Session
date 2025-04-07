package mutsa.practice.prac.article.controller;

import lombok.RequiredArgsConstructor;
import mutsa.practice.prac.article.entity.Article;
import mutsa.practice.prac.article.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/api/v1/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("api/v1/articles/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleService.findById(articleId));
    }

    @PostMapping("api/v1/articles")
    public ResponseEntity<Long> createArticle(@RequestBody Article article) {
        return ResponseEntity.status(201).body(articleService.createArticle(article));
    }

    @GetMapping("api/v1/articles/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String keyword) {
        return ResponseEntity.ok(articleService.findByKeyword(keyword));
    }
}
