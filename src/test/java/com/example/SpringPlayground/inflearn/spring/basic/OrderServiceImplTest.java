package com.example.SpringPlayground.inflearn.spring.basic;

import com.example.SpringPlayground.inflearn.spring.basic.domain.discount.FixDiscountPolicy;
import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Grade;
import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Member;
import com.example.SpringPlayground.inflearn.spring.basic.domain.order.Order;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemoryMemberRepository;
import com.example.SpringPlayground.inflearn.spring.basic.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 1000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
