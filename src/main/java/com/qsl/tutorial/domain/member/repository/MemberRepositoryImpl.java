package com.qsl.tutorial.domain.member.repository;

import com.qsl.tutorial.domain.member.entity.Member;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.qsl.tutorial.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Member getQslMember(Long id) {

    //QMember member = QMember.member;

    /*
    SELECT * FROM MEMBER WHERE id = #{id}
     */

    return queryFactory
        .selectFrom(member) // SELECT * FROM MEMBER
        .where(member.id.eq(id)) // WHERE id = #{id}
        .fetchOne(); // 단일 결과 반환

  }

  @Override
  public long getOneMemberCount() {
      return queryFactory.select(member.count()).from(member).fetchOne();
  }

  @Override
  public Member getOneMemberOrderByIdAscOne() {
      return queryFactory.selectFrom(member).orderBy(member.id.asc()).limit(1).fetchOne();
  }

  @Override
  public List<Member> getOneMembersOrderByIdAsc() {
    return queryFactory.selectFrom(member).orderBy(member.id.asc()).fetch();
  }

  @Override
  public List<Member> getOneMembersByNameAndEmail(String name, String email) {
    return queryFactory
        .selectFrom(member)
        .where(
            member.name.eq(name).and(member.email.eq(email))
        ).fetch();
  }

  @Override
  public List<Member> getOneMembersByNameOrEmail(String searchText) {
    return queryFactory
        .selectFrom(member)
        .where(
            member.name.eq(searchText).or(member.email.eq(searchText))
        ).fetch();
  }

  @Override
  public Page<Member> getMembersPage(String searchWord, Pageable pageable) {

    BooleanExpression predicate = member.email.containsIgnoreCase(searchWord)
        .or(member.name.containsIgnoreCase(searchWord));

    JPAQuery<Member> query = queryFactory
        .selectFrom(member)
        .where(predicate)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());

    // pageable 객체에 포함된 정렬 조건 (sort)을 기반으로 종덕 쿼리를 추가
    for (Sort.Order order : pageable.getSort()) {

      // Sort.Order 각가의 정렬 조건

      PathBuilder<Object> pathBuilder = new PathBuilder<>(member.getType(), member.getMetadata());

      query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));

    }

    List<Member> content = query.fetch();

    long total = queryFactory
        .select(member.count())
        .from(member)
        .fetchOne();

    return new PageImpl<>(content, pageable, total);

  }
}
