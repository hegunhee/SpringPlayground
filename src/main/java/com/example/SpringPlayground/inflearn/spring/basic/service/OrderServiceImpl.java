package com.example.SpringPlayground.inflearn.spring.basic.service;

import com.example.SpringPlayground.inflearn.spring.basic.domain.discount.DiscountPolicy;
import com.example.SpringPlayground.inflearn.spring.basic.domain.order.Order;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemberRepository;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemoryMemberRepository;
import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Member;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;

    }
}
