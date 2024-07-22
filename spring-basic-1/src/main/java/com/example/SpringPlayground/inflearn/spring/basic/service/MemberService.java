package com.example.SpringPlayground.inflearn.spring.basic.service;

import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
