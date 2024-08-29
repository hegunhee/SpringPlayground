package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import study.data_jpa.entity.Member;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class,id);
    }
}