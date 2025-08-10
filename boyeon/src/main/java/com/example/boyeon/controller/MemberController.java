package com.example.boyeon.controller;

import com.example.boyeon.dto.MemberForm;
import com.example.boyeon.entity.Article;
import com.example.boyeon.entity.Member;
import com.example.boyeon.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    // 특정 회원 조회, 전체 회원 조회 기능 추가
    @GetMapping("/signup")
    public String signUpPage() {
        return "members/new";
    }

    @PostMapping("/join")
    public String createArticle(MemberForm form) {
        log.info (form.toString());

        Member member = form.toEntity();
        log.info (member.toString());

        Member saved = memberRepository.save(member);
        log.info (saved.toString());
        return "";
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "member/index";
    }
}
