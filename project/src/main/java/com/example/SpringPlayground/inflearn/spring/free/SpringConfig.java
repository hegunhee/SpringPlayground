package com.example.SpringPlayground.inflearn.spring.free;

import com.example.SpringPlayground.inflearn.spring.free.repository.MemberRepository;
import com.example.SpringPlayground.inflearn.spring.free.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 직접 컴포넌트에 의존성을 설정하는 방법
 */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() { return new MemberService(memberRepository);}
}
