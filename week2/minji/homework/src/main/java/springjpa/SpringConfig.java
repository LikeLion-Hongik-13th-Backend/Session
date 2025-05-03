package springjpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springjpa.mutsa.repository.MemberRepository;
import springjpa.mutsa.repository.MemoryMemberRepository;
import springjpa.mutsa.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}