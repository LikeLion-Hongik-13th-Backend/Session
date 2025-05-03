package mutsa.firstproject.Article.repository;

import mutsa.firstproject.Article.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {
    //1. 게시물 생성
    Article save(Article article);
    //2. 모든 게시물 반환
    List<Article> findAll();
    //3. 게시물 조회
    Article findById(Long id);

}
