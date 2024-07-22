package com.example.SpringPlayground.inflearn.jpa.shop.service;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.Member;
import com.example.SpringPlayground.inflearn.jpa.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // public 메서드들에 트랜젝션이 걸리게됨
// readOnly로 성능최적화를 할 수 있음
@RequiredArgsConstructor // final 프로퍼티들로 이루어진 생성자만 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원 가입
     */
    @Transactional // 보다 세부적인 설정이 무조건 우선권을 가짐 이 메서드는 readOnly가 아님
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
