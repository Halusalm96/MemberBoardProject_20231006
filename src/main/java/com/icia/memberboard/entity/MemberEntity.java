package com.icia.memberboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String memberEmail;
    @Column(nullable = false, length = 50)
    private String memberPassword;
    @Column(nullable = false, length = 50)
    private String memberName;
    @Column
    private String memberMobile;
    @Column
    private String memberBirth;
}
