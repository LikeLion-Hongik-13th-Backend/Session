package mutlion.article.service;


import lombok.RequiredArgsConstructor;
import mutlion.article.entity.Article;
import mutlion.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Long createArticle(Article article){
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long Id) {
        System.out.println("서비스들어옴");
        return articleRepository.findById(Id);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}
