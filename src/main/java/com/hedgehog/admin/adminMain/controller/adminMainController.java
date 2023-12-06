package com.hedgehog.admin.adminMain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminmain")
public class adminMainController {

    /**
     *
     * @return 관리자 메인페이지 연결 메소드
     */
    @GetMapping("/main")
    public String adminmain(){
        return "admin/content/main/admin-main.html";
    }

}
