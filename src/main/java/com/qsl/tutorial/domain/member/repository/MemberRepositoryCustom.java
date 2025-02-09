package com.qsl.tutorial.domain.member.repository;

import com.qsl.tutorial.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    Member getQslMember(Long id);

    long getOneMemberCount();

    Member getOneMemberOrderByIdAscOne();

    List<Member> getOneMembersOrderByIdAsc();

    List<Member> getOneMembersByNameAndEmail(String name, String email);

    List<Member> getOneMembersByNameOrEmail(String searchText);

    Page<Member> getMembersPage(String searchWord, Pageable pageable);

}
