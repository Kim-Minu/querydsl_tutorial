package com.qsl.tutorial.member.repository;
import com.qsl.tutorial.domain.member.entity.Member;
import com.qsl.tutorial.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  @DisplayName("회원 생성")
  void createMember() {

      // {noop} : 비밀번호를 암호화 하지 않고 그대로 사용
      Member member1 = Member.builder()
          .name("김아무개")
          .password("{noop}1234")
          .email("user1@test.com")
          .build();

      Member member2 = Member.builder()
        .name("이아무개")
        .password("{noop}1234")
        .email("user2@test.com")
        .build();

      memberRepository.saveAll(Arrays.asList(member1, member2));

  }

  @Test
  @DisplayName("1번 회원을 Qsl로 가져오기")
  void getMemberById() {

    Member member1 = Member.builder()
        .name("김아무개")
        .password("{noop}1234")
        .email("user1@test.com")
        .build();

    memberRepository.save(member1);

    Member member = memberRepository.getQslMember(member1.getId());

    assertThat(member1.getId()).isEqualTo(member.getId());

  }


  @Test
  @DisplayName("전체 회원을 count 가져오기")
  void getOneMemberCount() {

    Member member1 = Member.builder()
        .name("김아무개")
        .password("{noop}1234")
        .email("user1@test.com")
        .build();

    memberRepository.save(member1);

    long memberCnt = memberRepository.getOneMemberCount();

    assertThat(memberCnt).isEqualTo(1);

  }


  @Test
  @DisplayName("가장 오래된 회원 1명 가져오기")
  void getOneMemberOrderByIdAscOne() {

    Member member1 = Member.builder()
        .name("김아무개")
        .password("{noop}1234")
        .email("user1@test.com")
        .build();

    Member member2 = Member.builder()
        .name("이아무개")
        .password("{noop}1234")
        .email("user2@test.com")
        .build();

    memberRepository.saveAll(Arrays.asList(member1, member2));

    Member oldMember = memberRepository.getOneMemberOrderByIdAscOne();

    assertThat(oldMember.getId()).isEqualTo(member1.getId());

  }

  @Test
  @DisplayName("전체회원, 오래된 순")
  void getOneMembersOrderByIdAsc() {


    Member member1 = Member.builder()
        .name("김아무개")
        .password("{noop}1234")
        .email("user1@test.com")
        .build();

    Member member2 = Member.builder()
        .name("이아무개")
        .password("{noop}1234")
        .email("user2@test.com")
        .build();

    memberRepository.saveAll(Arrays.asList(member1, member2));


    List<Member> memberList = memberRepository.getOneMembersOrderByIdAsc();

    assertThat(memberList.size()).isEqualTo(2);
    assertThat(memberList.stream().findFirst().get().getId()).isEqualTo(member1.getId());

  }

  @Test
  @DisplayName("검색, List 리턴, 검색 대상 : name, email")
  void getOneMembersByNameAndEmail() {

    String name = "<UNK>";
    String email = "<UNK>";

    List<Member> memberList =  memberRepository.getOneMembersByNameAndEmail(name, email);

  }

  @Test
  @DisplayName("페이징")
  void getMembersPage() {

    int page = 0;
    int size = 1;

    Sort sort = Sort.by(
        Sort.Order.asc("id"),
        Sort.Order.desc("name")
    );

    Pageable pageable = PageRequest.of(page, size, sort);
    String searchWord = "user1";


    Member member1 = Member.builder()
        .name("김아무개")
        .password("{noop}1234")
        .email("user1@test.com")
        .build();

    Member member2 = Member.builder()
        .name("이아무개")
        .password("{noop}1234")
        .email("user2@test.com")
        .build();

    memberRepository.saveAll(Arrays.asList(member1, member2));

    Page<Member> members = memberRepository.getMembersPage(searchWord, pageable);


  }

  @Test
  @DisplayName("회원에게 관심사를 등록할 수 있다.")
  void addInterestKeyword() {
    Member member = memberRepository.getQslMember(1L);

    member.addInterestKeywordContent("테니스");
    member.addInterestKeywordContent("오버워치");
    member.addInterestKeywordContent("헬스");
    member.addInterestKeywordContent("런닝");


  }



}