package com.hedgehog.client.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myshop")
public class MyshopController {

    @GetMapping("mypage")
    public String mypage() {
        return "/client/content/myshop/mypageMember.html";
    }

    @GetMapping("memberInfoChange")
    public String infoChange() {
        return "/client/content/myshop/memberInfoChange.html";
    }
    @GetMapping("mypageGuest")
    public String guest() {
        return "/client/content/myshop/mypageGuest.html";
    }
    @GetMapping("info")
    public String memberInfo() {
        return "/client/content/myshop/memberInfo.html";
    }
}
