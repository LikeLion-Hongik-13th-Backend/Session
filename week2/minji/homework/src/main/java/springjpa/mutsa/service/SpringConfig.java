package springjpa.mutsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springjpa.mutsa.aop.TimeTraceAop;
import springjpa.mutsa.repository.JdbcTemplateMemberRepository;
import springjpa.mutsa.repository.MemberRepository;
import springjpa.mutsa.repository.MemoryMemberRepository;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
     @Bean
    public MemberRepository memberRepository() {
         return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
     }
}