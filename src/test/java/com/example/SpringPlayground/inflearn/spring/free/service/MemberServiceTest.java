package com.example.SpringPlayground.inflearn.spring.free.service;

import com.example.SpringPlayground.inflearn.spring.free.domain.Member;
import com.example.SpringPlayground.inflearn.spring.free.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    /**
     * memberService의 경우 외부에서 의존성을 설정함
     * 즉 의존성 주입(DI)을 하고있음
     */
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given (주어지는 데이터)
        Member member = new Member();
        member.setName("spring");
        //when (검증하는것)
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 해당 람다를 진행시켰을때 해당 Exception이 터져야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}