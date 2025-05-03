package mutsa.firstproject.Article.service;
import lombok.RequiredArgsConstructor;
import mutsa.firstproject.Article.Article;
import mutsa.firstproject.Article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    // 1. 게시물 생성
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
    // 2. 게시물 목록 조회
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    // 3. 게시물 조회
    public Article findOneArticle(Long id) {
        return articleRepository.findById(id);
    }
}

