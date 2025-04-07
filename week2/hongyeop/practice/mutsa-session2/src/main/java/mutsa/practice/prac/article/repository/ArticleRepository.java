package mutsa.practice.prac.article.repository;

import lombok.extern.slf4j.Slf4j;
import mutsa.practice.prac.article.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ArticleRepository {
    private long PRIMARY_ID = 1;

    private Map<Long, Article> database = new HashMap<>();

    public List<Article> findAll() {
        return new ArrayList<>(database.values());
    }

    public Long save(Article article) {
        log.info("Primary ID: " + PRIMARY_ID);
        database.put(PRIMARY_ID, article);
        return PRIMARY_ID++;
    }

    public Article findById(Long id) {
        return database.get(id);
    }

    public List<Article> findByKeyword(String keyword) {
        List<Article> articles = new ArrayList<>();
        for (Article article : database.values()) {
            if (article.getTitle().contains(keyword) || article.getDescription().contains(keyword))
                articles.add(article);
        }
        return articles;
    }

}
