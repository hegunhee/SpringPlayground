package com.example.SpringPlayground.inflearn.spring.basic.beanfind;

import com.example.SpringPlayground.inflearn.spring.basic.AppConfig;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberService;
import com.example.SpringPlayground.inflearn.spring.basic.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * 인터페이스가 아니더라도 구체타입으로 조회도 가능함
     * 하지만 해당 방식은 이상적이지 않음(추상화에 의존해야하는데 구현에 의존)
     */
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2 () {
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx",MemberService.class));
        // 해당 람다를 실행했을때 첫번째 파라미터 Exception이 터지면 성공
    }
}
