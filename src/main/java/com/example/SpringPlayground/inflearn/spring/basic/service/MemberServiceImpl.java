package com.example.SpringPlayground.inflearn.spring.basic.service;

import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Member;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemberRepository;
import com.example.SpringPlayground.inflearn.spring.basic.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
