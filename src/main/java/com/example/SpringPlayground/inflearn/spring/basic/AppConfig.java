package com.example.SpringPlayground.inflearn.spring.basic;

import com.example.SpringPlayground.inflearn.spring.basic.domain.discount.DiscountPolicy;
import com.example.SpringPlayground.inflearn.spring.basic.domain.discount.FixDiscountPolicy;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemberRepository;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemoryMemberRepository;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberService;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberServiceImpl;
import com.example.SpringPlayground.inflearn.spring.basic.service.OrderService;
import com.example.SpringPlayground.inflearn.spring.basic.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 메서드 이름으로 등록되는것이 디폴트
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
