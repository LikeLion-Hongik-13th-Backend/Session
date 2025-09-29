package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //component scan의 대상이 됨
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext //entity manager 자동 주입
    private final EntityManager em;

    //멤버 저장
    public void save(Member member){
        em.persist(member);
    }

    //멤버 단건 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //멤버 전체 조회
    public List<Member> findAll() {
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        return members;
    }

    //이름으로 멤버 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =: name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
