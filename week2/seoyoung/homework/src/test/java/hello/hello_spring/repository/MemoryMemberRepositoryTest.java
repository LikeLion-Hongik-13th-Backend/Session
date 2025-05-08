package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

//얘는 굳이 public으로 안해도 됨 !!
class MemoryMemberRepositoryTest {
MemoryMemberRepository repository = new MemoryMemberRepository();

//이 메서드가 실행이 끝날때마다 어떤 동작을 하는 것임
@AfterEach
public void afterEach()
{
    repository.clearStore();

    // 이렇게 되면 .. 테스트가 실행되고 끝날때마다
    //한번씩 저장소를 다 지움
    //순서와 상관없어짐
}
//얘가 동작하는지 체크해보면 됨
    @Test
    //그러면 이제 save를 실행할 수 있음 !
public void save()
{
Member member =new Member();
member.setName("spring");

repository.save(member);

Member result=repository.findById(member.getId()).get();
//get으로 꺼내는게 그렇게 좋은 방법은 아니지만, 테스트코드에서는 자주 사용
   // System.out.println("result="+(result ==member));
    //Assertions.assertEquals(result,member);
    //Assertions에서 junit을 썻엇는데, 요새는 assertj를 많이 씀
    assertThat(member).isEqualTo(result);
}

@Test

    public void findByName()
{
    Member member1=new Member();
    member1.setName("spring1");
    repository.save(member1);

    //복사해서 rename 할때 .. shift +F6
    Member member2=new Member();
    member2.setName("spring2");
    repository.save(member1);

    //이거를 spring2로 바꾸면 빨간불 뜸 .
    Member result =repository.findByName("spring1").get();
    assertThat(result).isEqualTo(member1);


}
    @Test
    public void findAll()
    {
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
