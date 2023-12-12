package com.hedgehog.client.clientOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/clientOrder")
public class ClientOrderController {
    @GetMapping("/cartOrder")
    public String clientOrder() {


        return "/client/content/clientOrder/cartOrder";
    }
}
