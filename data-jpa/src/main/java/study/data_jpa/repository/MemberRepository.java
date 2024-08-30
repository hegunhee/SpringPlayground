package study.data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
    //인터페이스를 보고 프록시를 만들어줌 구현체는 Spring Data JPA가 만들어줌
    //JPA 예외처리도 자동으로 Spring예외로 변경해줌
}
