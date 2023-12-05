package com.hedgehog.client.join.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class JoinController {
    @GetMapping("/regist")
    public String regist(){
        return "/client/content/join/regist";
    }
}
