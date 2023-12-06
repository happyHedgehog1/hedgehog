package com.hedgehog.admin.adminEvent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class adminEventListController {
    /**
     *
     * @return 이벤트 목록 페이지 연결 메소드
     */
    @GetMapping("/eventList")
    private String eventList(){
        return "admin/content/event/eventList.html";
    }

    /**
     *
     * @return 이벤트 상품 추가 페이지 연결 메소드
     */
    @GetMapping("/eventProdAdd")
    private String eventProdAdd(){
        return "admin/content/event/eventProdAdd.html";
    }
}
