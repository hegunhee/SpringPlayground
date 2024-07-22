package com.example.SpringPlayground.inflearn.spring.free.repository;

import com.example.SpringPlayground.inflearn.spring.free.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JSQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}

