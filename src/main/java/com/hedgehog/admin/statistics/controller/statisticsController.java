package com.hedgehog.admin.statistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class statisticsController {
    @GetMapping("/statistics")
    public String statistics(){
        return "admin/content/statistics/statistics.html";
    }
}
