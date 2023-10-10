package com.icia.memberboard.controller;

import com.icia.memberboard.dto.MemberDTO;
import com.icia.memberboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/save")
    public String memberSave() {
        return "memberPages/memberSave";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws Exception {
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @PostMapping("/member/check")
    public ResponseEntity checkEmail(@RequestBody MemberDTO memberDTO) {
        boolean result = memberService.checkEmail(memberDTO.getMemberEmail());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/member/login")
    public String memberLogin() {
        return "/memberPages/memberLogin";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean result = memberService.findByMemberEmailAndMemberPassword(memberDTO);
        if(result){
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "/memberPages/memberMain";
        }else {
            return "/memberPages/memberLogin";
        }
    }
    @GetMapping("/member/detail/{loginEmail}")
    public String  memberDetail(Model model,@PathVariable("loginEmail") String loginEmail) {
        System.out.println(loginEmail);
        try {
            MemberDTO memberDTO1 = memberService.findByMemberEmail(loginEmail);
            model.addAttribute("member", memberDTO1);
            return "memberPages/memberDetail";
        } catch (Exception e) {
            return "/NotFound";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginEmail");
        return "/index";
    }
    @GetMapping("/member/update/{id}")
    public String  detailAxios(@PathVariable("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "/memberPages/memberUpdate";
    }
    @DeleteMapping("/member/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/member/{id}")
    public ResponseEntity update(@RequestBody MemberDTO memberDTO, HttpSession session) {
        memberService.update(memberDTO);
        session.removeAttribute("loginEmail");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
