package springjpa.mutsa.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springjpa.mutsa.domain.Member;

import java.util.Optional;

@Repository
@Primary
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //select m from Member m where m.name=?
    Optional<Member> findByName(String name);
}
