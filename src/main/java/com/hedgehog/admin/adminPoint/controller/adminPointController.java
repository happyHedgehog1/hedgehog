package com.hedgehog.admin.adminPoint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/point")
public class adminPointController {

    @GetMapping("/pointA")
    public String pointA(){
        return "admin/content/point/pointA.html";

    }

    @GetMapping("/pointB")
    public String pointB(){
        return "admin/content/point/pointB.html";

    }
}
