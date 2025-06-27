package mutsa.ex4.repository;

import mutsa.ex4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.post.postId = :postId")
    List<Comment> findAllByPostId(@Param("postId") Long postId);
    //postId가 아닌 comment.post.id을 자동으로 찾아서 error 발생하여 명시적으로 설정
}
