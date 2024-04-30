package com.example.SpringPlayground.inflearn;

import com.example.SpringPlayground.inflearn.repository.MemberRepository;
import com.example.SpringPlayground.inflearn.repository.MemoryMemberRepository;
import com.example.SpringPlayground.inflearn.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 직접 컴포넌트에 의존성을 설정하는 방법
 */
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
