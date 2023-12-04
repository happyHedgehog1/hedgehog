package com.hedgehog.admin.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class serviceController {

    @GetMapping("/productInquiry")
    public String productInquiry(){
        return "admin/Service/Product-inquiry.html";
    }
}
