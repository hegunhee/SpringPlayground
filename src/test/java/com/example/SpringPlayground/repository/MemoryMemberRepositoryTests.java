package com.example.SpringPlayground.repository;

import com.example.SpringPlayground.inflearn.domain.Member;
import com.example.SpringPlayground.inflearn.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTests {


    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 하나의 테스트가 끝날때마다 작동되는 메서드
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    /**
     * 모든 테스트는 순서가 보장되지 않음
     */

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // 읽기 쉬움
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
