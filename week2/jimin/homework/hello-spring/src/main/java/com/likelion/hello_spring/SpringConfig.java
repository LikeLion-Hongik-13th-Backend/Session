package com.likelion.hello_spring;

import com.likelion.hello_spring.repository.*;
import com.likelion.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig
{
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService()
    {
        return new MemberService(memberRepository);
    }


    //** SpringDataJpa 적용 전 Spring Config **//
    /*
    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService()
    {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository()
    {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    */
}
