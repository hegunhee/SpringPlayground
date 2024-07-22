package com.example.SpringPlayground.inflearn.spring.basic.repository;

import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
