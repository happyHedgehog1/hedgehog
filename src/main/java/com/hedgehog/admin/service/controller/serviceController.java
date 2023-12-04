package com.hedgehog.admin.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Service")
public class serviceController {

    @GetMapping("/productInquiry")
    public String productInquiry(){
        return "admin/content/Service/Product-inquiry.html";
    }

    @GetMapping("/mail")
    public String mail(){
        return "admin/content/Service/email.html";
    }
}
