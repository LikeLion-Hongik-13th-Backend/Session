package mutlion.article.repository;

import mutlion.article.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
    private Long PRIMARY_ID = 1L;
    private Map<Long, Article> database = new HashMap<>();

    public Long save(Article article) {
        database.put(PRIMARY_ID, article);
        return PRIMARY_ID++;
    }

    public List<Article> findAll() {
        return new ArrayList<>(database.values());
    }

    public Article findById(Long id) {
        System.out.println("리포지토리들어옴");
        return database.get(id);
    }

    public List<Article> findByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        for(Article article : database.values()){
            if(article.title.contains(title))
                articles.add(article);
        }
        return articles;
    }


}
