package com.hedgehog.admin.adminPoint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/point")
public class adminPointController {

    /**
     *
     * @return 적립금 설정 페이지 연결 메소드
     */
    @GetMapping("/pointA")
    public String pointA(){
        return "admin/content/point/pointA.html";

    }

    /**
     *
     * @return 적립금 내역 조회 페이지 연결 메소드
     */
    @GetMapping("/pointB")
    public String pointB(){
        return "admin/content/point/pointB.html";

    }
}
