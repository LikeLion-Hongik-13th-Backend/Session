package springjpa.mutsa.repository;

import jakarta.persistence.EntityManager;
import springjpa.mutsa.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // 데이터 소스를 내부적으로 들고 있어서 DB랑 통신하고 처리함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // 객체를 대상으로 쿼리 날리기
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery(
                        "select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
