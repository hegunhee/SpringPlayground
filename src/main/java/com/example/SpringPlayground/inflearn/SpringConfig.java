package com.example.SpringPlayground.inflearn;

import com.example.SpringPlayground.inflearn.repository.JpaMemberRepository;
import com.example.SpringPlayground.inflearn.repository.MemberRepository;
import com.example.SpringPlayground.inflearn.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 직접 컴포넌트에 의존성을 설정하는 방법
 */
@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}
