package prac1.mutsa2.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prac1.mutsa2.User.entity.Follow;
import prac1.mutsa2.User.entity.User;
@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User follower, User following);

}
