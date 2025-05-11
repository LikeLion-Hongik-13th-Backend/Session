package prac1.mutsa2.Post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prac1.mutsa2.Post.entity.Post;
import prac1.mutsa2.Post.entity.PostLike;
import prac1.mutsa2.User.entity.User;

import java.util.Optional;
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserAndPost(User user, Post post);
    Optional<PostLike> findByUserAndPost(User user, Post post);
}
