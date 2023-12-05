package com.hedgehog.client.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("myshop")
public class MyshopController {
    @GetMapping("/mypage")
    public String mypage(){
        return "client/content/myshop/mypageMember.html";
    }
}
