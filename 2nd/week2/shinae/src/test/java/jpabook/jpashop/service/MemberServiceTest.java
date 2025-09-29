package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) //테스트 실행 시 스프링과 엮어서 테스트
@SpringBootTest //스프링 컨테이너 안에서 테스트 돌림
@Transactional // 테스트 안에서 사용 시 롤백함
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false)//저장 정보들을 roll back 하지 않기 때문에 등록 쿼리 등 볼 수 있음
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("kim");

        Long memberId = memberService.join(member);

     //   em.flush();
        Assert.assertEquals(member, memberRepository.findOne(memberId));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 중복_회원_예외() throws Exception{
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
//        try {
//            memberService.join(member2); //예외가 발생해야 한다!
//        } catch (IllegalStateException e) {
//            return;
//        }
        memberService.join(member2);

        fail("예외가 발생해야 한다");
    }
}