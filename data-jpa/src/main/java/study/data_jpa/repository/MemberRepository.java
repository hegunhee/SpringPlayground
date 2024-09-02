package study.data_jpa.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    //인터페이스를 보고 프록시를 만들어줌 구현체는 Spring Data JPA가 만들어줌
    //JPA 예외처리도 자동으로 Spring예외로 변경해줌

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findMember(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.data_jpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username); // 컬렉션

    Member findMemberByUsername(String username); // 단건

    Optional<Member> findOptionalMemberByUsername(String username); // 단건 Optional

    @Query(value = "select m from Member m left join m.team t",
        countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    // 해당 어노테이션이 없으면 update가 진행되지 않음 (Exception 발생, DML을 사용할 수 없습니다...)
    @Modifying(clearAutomatically = true) // clearAutomatically 설정을 한다면 영속성 컨텍스트를 날리지않아도됨
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    //쿼리 설정없이 페치조인을 도와주는게 @EntityGraph
    @Query("select m from Member m left join fetch m.team") // 한방쿼리로 다 끌어옴
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    // @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    // 과연 이게 효율적인가? 암달의 법칙을 참고
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly",value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.WRITE)
    List<Member> findLockByUsername(String username);
}
