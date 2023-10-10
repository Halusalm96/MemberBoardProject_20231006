package com.icia.memberboard.dto;

import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.util.UtilClass;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;
    private String memberBirth;
    private String createdAt;

    public static MemberDTO toMemberList(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberBirth(memberEntity.getMemberBirth());
        memberDTO.setCreatedAt((UtilClass.dateTimeFormat(memberEntity.getCreatedAt())));
        return memberDTO;
    }
}
