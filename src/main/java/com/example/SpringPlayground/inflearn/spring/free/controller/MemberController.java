package com.example.SpringPlayground.inflearn.spring.free.controller;

import com.example.SpringPlayground.inflearn.spring.free.domain.Member;
import com.example.SpringPlayground.inflearn.spring.free.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     *
     * @return 값에 해당하는 tamplate의 html을 조회
     */
    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /**
     * POST이므로 데이터를 등록해줌 form action이 해당 url
     * MemberForm의 name 값이 들어옴
     * @param form
     * @return
     */
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
