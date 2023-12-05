package com.hedgehog.admin.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class orderController {

    @GetMapping("/order")
    public String order(){
        return "admin/content/order/order.html";
    }
}
