package com.hedgehog.admin.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class memberController {

    @GetMapping("/member")
    public String memberList(){
        return "admin/content/member/member.html";
    }


    @GetMapping("/unregister")
    public String unregisterList(){
        return "admin/content/member/unregister.html";
    }


}
