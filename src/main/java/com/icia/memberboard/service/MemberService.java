package com.icia.memberboard.service;

import com.icia.memberboard.dto.MemberDTO;
import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public boolean checkEmail(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    public boolean findByMemberEmailAndMemberPassword(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword());
        if (optionalMemberEntity.isPresent()) {
            return true;
        }else {
            return false;
        }
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> memberRepositoryById = memberRepository.findById(id);
        if (memberRepositoryById.isPresent()) {
            MemberEntity memberEntity = memberRepositoryById.get();
            return MemberDTO.toMemberList(memberEntity);
        } else {
            return null;
        }
    }

    public boolean memberDetail(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public MemberDTO findByEmail(String memberEmail) {
        Optional<MemberEntity> memberRepositoryById = memberRepository.findByMemberEmail(memberEmail);
        if (memberRepositoryById.isPresent()) {
            MemberEntity memberEntity = memberRepositoryById.get();
            return MemberDTO.toMemberList(memberEntity);
        } else {
            return null;
        }
    }
}
