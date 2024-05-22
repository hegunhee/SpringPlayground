package com.example.SpringPlayground.inflearn.jpa.shop.service;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.Member;
import com.example.SpringPlayground.inflearn.jpa.shop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;
    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member,memberRepository.findOne(savedId));
    }

    // Junit5의 경우 Test어노테이션 안에 except value가 없음
    @Test
    public void 회원_중복_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when

        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class,() -> {
            memberService.join(member2);
        });

        //then
    }
}