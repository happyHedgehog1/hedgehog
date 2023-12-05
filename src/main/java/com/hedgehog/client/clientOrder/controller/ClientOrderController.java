package com.hedgehog.client.clientOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientOrder")
public class ClientOrderController {
    @GetMapping("/cartOrder")
    public String clientOrder() {
        return "/client/content/clientOrder/cartOrder";
    }
}
