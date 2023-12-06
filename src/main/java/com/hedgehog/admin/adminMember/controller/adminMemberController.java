package com.hedgehog.admin.adminMember.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class adminMemberController {

    /**
     *
     * @return 회원조회 페이지 연결 메소드
     */
    @GetMapping("/member")
    public String memberList(){
        return "admin/content/member/member.html";
    }

    /**
     *
     * @return 탈퇴 회원 조회 페이지 연결 메소드
     */
    @GetMapping("/unregister")
    public String unregisterList(){
        return "admin/content/member/unregister.html";
    }


}
