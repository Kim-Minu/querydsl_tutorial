package com.qsl.tutorial.domain.member.entity;

import com.qsl.tutorial.domain.interest.entity.Interest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  private String password;

  @Column(unique = true, nullable = false)
  private String email;

}
