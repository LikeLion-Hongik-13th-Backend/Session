package mutlion.article.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mutlion.article.entity.Article;
import mutlion.article.service.ArticleService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/api/v1/article")
    public Long createArticle(@RequestBody Article article){
        log.info("으악");
        return articleService.createArticle(article);
    }

    @GetMapping("/api/v1/articles")
    public ResponseEntity<List<Article>> findAllArticles(){
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/api/v1/article/{articleId}")
    public ResponseEntity<Article> getOneArticle(@PathVariable("articleId") Long articleId){
        log.info("컨트롤러들어옴");
        return ResponseEntity.ok(articleService.findById(articleId));
    }

    @GetMapping("/api/v1/search")
    public List<Article> searchArticle(@RequestParam String title) {
        return articleService.findByTitle(title);
    }
}
