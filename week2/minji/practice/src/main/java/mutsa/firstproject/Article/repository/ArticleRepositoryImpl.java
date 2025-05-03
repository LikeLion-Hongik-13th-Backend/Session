package mutsa.firstproject.Article.repository;

import mutsa.firstproject.Article.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final Map<Long, Article> storage = new HashMap<>();
    private Long id = 1L;

    @Override
    public Article save(Article article) {
        article.setId(id++);
        storage.put(article.getId(), article);
        return article;
    }
    @Override
    public List<Article> findAll() {
        return new ArrayList<>(storage.values());
    }
    @Override
    public Article findById(Long id) {
        return storage.get(id);
    }

}


