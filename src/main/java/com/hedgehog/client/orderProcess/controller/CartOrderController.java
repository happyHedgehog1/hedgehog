package com.hedgehog.client.orderProcess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderProcess")
public class CartOrderController {
    @GetMapping( value = {"/", "/cartOrder"})
    public String cartOrder() {

        return "client/content/orderProcess/cartOrder.html";
    }

}
