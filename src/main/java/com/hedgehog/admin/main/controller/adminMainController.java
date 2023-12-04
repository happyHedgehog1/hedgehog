package com.hedgehog.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminmain")
public class adminMainController {
    @GetMapping("/main")
    public String adminmain(){
        return "admin/content/main/admin-main.html";
    }

}
