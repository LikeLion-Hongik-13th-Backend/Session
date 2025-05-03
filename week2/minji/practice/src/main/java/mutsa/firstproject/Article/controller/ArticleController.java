package mutsa.firstproject.Article.controller;

import lombok.RequiredArgsConstructor;
import mutsa.firstproject.Article.Article;
import mutsa.firstproject.Article.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping("/article")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleService.createArticle(article);
        return ResponseEntity.ok(savedArticle);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getArticles(){
        return ResponseEntity.ok(articleService.getArticles());
    }
    @GetMapping("/article/{id}")
    public ResponseEntity<Article> selectArticle(@PathVariable Long id){
        Article found = articleService.findOneArticle(id);
        return ResponseEntity.ok(found);
    }
}
