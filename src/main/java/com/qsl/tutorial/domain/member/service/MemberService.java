package com.qsl.tutorial.domain.member.service;

import com.qsl.tutorial.domain.member.entity.Member;
import com.qsl.tutorial.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getQslMember(Long id) {
      return memberRepository.getQslMember(id);
    }

}
