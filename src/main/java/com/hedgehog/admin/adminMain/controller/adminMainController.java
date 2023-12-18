package com.hedgehog.admin.adminMain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminmain")
public class adminMainController {

    /**
     *
     * @return 관리자 메인페이지 연결 메소드
     */
    @GetMapping("/main")
    public ModelAndView adminmain(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/content/main/admin-main");




        return mv;
    }

}
