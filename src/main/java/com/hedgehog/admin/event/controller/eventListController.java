package com.hedgehog.admin.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class eventListController {

    @GetMapping("/eventList")
    private String eventList(){
        return "admin/content/event/eventList.html";
    }

    @GetMapping("/eventProdAdd")
    private String eventProdAdd(){
        return "admin/content/event/eventProdAdd.html";
    }
}
