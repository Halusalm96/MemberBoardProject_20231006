package com.icia.memberboard.controller;

import com.icia.memberboard.dto.MemberDTO;
import com.icia.memberboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/save")
    public String memberSave(){
        return "memberPages/memberSave";
    }
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws Exception{
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }
}
