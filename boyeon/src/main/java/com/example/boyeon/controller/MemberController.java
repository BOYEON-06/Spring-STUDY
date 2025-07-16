package com.example.boyeon.controller;

import com.example.boyeon.dto.MemberForm;
import com.example.boyeon.entity.Member;
import com.example.boyeon.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    public String newArticleForm() { return "members/new"; }

    @PostMapping("/join")
    public String createArticle(MemberForm form) {
        System.out.println(form.toString());

        Member member = form.toEntity();
        System.out.println(member.toString());

        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());

        return "";
    }
}
