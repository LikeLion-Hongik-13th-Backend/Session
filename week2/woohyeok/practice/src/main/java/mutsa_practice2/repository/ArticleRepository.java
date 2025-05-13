package com.example.mutsa_practice2.repository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.example.mutsa_practice2.entity.Article;
import java.util.*;

@Repository
@Getter
@Slf4j
public class ArticleRepository {
    private long PRIMARY_ID = 1;

    //DB
    private Map<Long, Article> database = new HashMap<>();

    public Long save(Article article) {
        log.info("Primary ID: " + PRIMARY_ID);
        database.put(PRIMARY_ID, article);
        return PRIMARY_ID++;
    }

    public Article findById(long id) {
        return database.get(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(database.values());
    }

    public List<Article> findByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        for (Article article : database.values()) {
            if(article.getTitle().contains(title)) {
                //단어가 타이틀에 있으면 나오도록
                articles.add(article);
            }
        }
        return articles;
    }
}
