package com.hedgehog.admin.adminEvent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class adminEventListController {

    @GetMapping("/eventList")
    private String eventList(){
        return "admin/content/event/eventList.html";
    }

    @GetMapping("/eventProdAdd")
    private String eventProdAdd(){
        return "admin/content/event/eventProdAdd.html";
    }
}
