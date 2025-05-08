package hello.hello_spring.repository;


import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static  long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        //회원 ID 설정, sequence 값 증가 (회원 수 +1)
        store.put(member.getId(), member); //map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }//null일 경우 optional이 감싸서 반환

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //맵 순회하면서 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //values에 있는 회원들 반환
    }

    public void clearStore(){
        store.clear();
    }
}