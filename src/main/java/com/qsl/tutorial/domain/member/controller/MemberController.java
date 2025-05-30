package com.qsl.tutorial.domain.member.controller;

import com.qsl.tutorial.domain.member.service.MemberService;
import com.qsl.tutorial.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public Member getQslMember(@PathVariable Long id) {
        return memberService.getQslMember(id);
    }

}
