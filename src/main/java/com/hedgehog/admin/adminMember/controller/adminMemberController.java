package com.hedgehog.admin.adminMember.controller;


import com.hedgehog.admin.adminMember.model.service.adminMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Slf4j
public class adminMemberController {

    @Autowired
    private adminMemberService adminMemberService;

    @GetMapping("/admin/member")


    @GetMapping("/member")
    public String memberList(){ return "admin/content/member/member.html";
    }


    @GetMapping("/unregister")
    public String unregisterList(){
        return "admin/content/member/unregister.html";
    }




}
