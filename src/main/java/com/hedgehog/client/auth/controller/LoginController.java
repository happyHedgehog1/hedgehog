package com.hedgehog.client.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "client/content/auth/login.html";
    }

    @GetMapping("/searchId")
    public String searchId() {
        return "/client/content/auth/searchId.html";
    }

    @GetMapping("/searchPassword")
    public String searchPassword() {
        return "/client/content/auth/searchPassword.html";
    }

    @GetMapping("/regist")
    public String regist(){
        return "/client/content/auth/regist";
    }

}

