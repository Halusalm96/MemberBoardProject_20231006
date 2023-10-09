package com.icia.memberboard.entity;

import com.icia.memberboard.dto.MemberDTO;
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

    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        return memberEntity;
    }
}
