package mutsa.practice.prac.article.service;

import lombok.RequiredArgsConstructor;
import mutsa.practice.prac.article.entity.Article;
import mutsa.practice.prac.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id);
    }

    public Long createArticle(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> findByKeyword(String keyword) {
        return articleRepository.findByKeyword(keyword);
    }

}
