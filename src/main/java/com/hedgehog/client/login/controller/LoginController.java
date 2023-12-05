package com.hedgehog.client.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "client/content/login/login.html";
    }

    @GetMapping("/searchId")
    public String searchId() {
        return "/client/content/login/searchId.html";
    }

    @GetMapping("/searchPassword")
    public String searchPassword() {
        return "/client/content/login/searchPassword.html";
    }

}

