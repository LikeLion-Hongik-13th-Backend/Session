package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);//optional은 null처리 방법
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
