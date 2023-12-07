package com.hedgehog.client.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String defaultLocation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("권한: " + authentication.getAuthorities());
        return "client/content/main/main";
    }

}
