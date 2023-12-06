package com.hedgehog.admin.adminOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class adminOrderController {

    /**
     *
     * @return 주문내역 페이지 연결 메소드
     */
    @GetMapping("/order")
    public String order(){
        return "admin/content/order/order.html";
    }
}
