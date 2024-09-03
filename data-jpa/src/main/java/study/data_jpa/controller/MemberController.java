package study.data_jpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;
import study.data_jpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/member2/{id}")
    public String findMember(@PathVariable("id") Member member) { // Repository가 있다면 바로 조회 가능
        return member.getUsername();
    }

    //@PageableDefault로 기본 설정을 할 수 있음 당연히 글로벌보다는 이것이 더 우선순위가 높음
    @GetMapping("/members") // query 정보로 페이징 크기나 페이지를 조절 가능함
    public Page<MemberDto> list(@PageableDefault(size =5,sort = "username") Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(1, 2);// pageable을 만들지 말고 직접 PageRequest를 사용하는 방법이 있음
// Page도 반환하지 않고 사용하는것이좋음
        return memberRepository.findAll(pageable)
                .map(MemberDto::new);
    }

//    @PostConstruct
    public void init() {
        for(int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
