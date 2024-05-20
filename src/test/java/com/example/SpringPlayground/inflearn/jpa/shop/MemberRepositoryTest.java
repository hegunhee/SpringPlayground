package com.example.SpringPlayground.inflearn.jpa.shop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 만약 test code의 패키지와 메인코드의 패키지가 다르다면
 * 같게 맞춰주거나 @SpringBootTest(classes = SpringBootClass.class)로 바꿔줘야함
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Entity를 사용할때는 @Transactional 어노테이션이 존재해야함
     *  테스트케이스에 @Transactional 어노테이션이 있다면 테스트가 끝나고 데이터를 롤백해버림 (@Rollback 어노테이션도 존재함)
     */
    @Test
    @Transactional
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");
        //when
        Long savedId = memberRepository.save(member);
        //then

        Member findMember = memberRepository.find(savedId);
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member = " + (findMember == member));
    }
}
