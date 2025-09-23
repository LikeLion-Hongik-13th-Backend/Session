package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@AllArgsConstructor //생성자 다 만들어줌
@RequiredArgsConstructor // final이 있는 필드만 생성자 만들어줌 추천!
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
//    //setter injection 장점 : 테스트 할 때 mock 직접 주입 가능
//    private void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//    @Autowired
//    //권장되는 방법 이유 : set으로 수정 불가, 의존성 파악 용이
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    } @AllArgsConstructor을 쓰면 필요 없음

    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복회원 방지 로직
    private void validateDuplicateMember(Member member){
        //Exception
        List<Member> members = memberRepository.findByName(member.getName());
        if(members.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)
    //데이터 수정이 없는 조회시엔 readOnly = ture 옵션을 붙여주면 성능 향상
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    @Transactional(readOnly = true)
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }
}
