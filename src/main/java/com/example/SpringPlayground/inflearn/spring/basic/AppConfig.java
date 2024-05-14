package com.example.SpringPlayground.inflearn.spring.basic;

import com.example.SpringPlayground.inflearn.spring.basic.domain.discount.FixDiscountPolicy;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemoryMemberRepository;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberService;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberServiceImpl;
import com.example.SpringPlayground.inflearn.spring.basic.service.OrderService;
import com.example.SpringPlayground.inflearn.spring.basic.service.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
