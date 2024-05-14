package com.example.SpringPlayground.inflearn.spring.basic;

import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Grade;
import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Member;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberService;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join() {
        // given (어떤 상황들이 주어지고)
        Member member = new Member(1L,"memberA", Grade.VIP);
        // when (어떤일이 일어났을때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then (결과)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
