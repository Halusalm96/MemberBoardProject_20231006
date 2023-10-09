package com.icia.memberboard.service;

import com.icia.memberboard.dto.MemberDTO;
import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
