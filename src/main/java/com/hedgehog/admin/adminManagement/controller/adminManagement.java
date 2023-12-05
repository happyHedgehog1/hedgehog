package com.hedgehog.admin.adminManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminManagement")
public class adminManagement {

    /**
     *
     * @return 관리자 관리 페이지 연결 메소드
     */
    @GetMapping("/adminManagement")
    public String adminManagement(){
        return "admin/content/adminManagement/adminManagement.html";
    }

}
