package com.hedgehog.client.basket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @GetMapping(value = {"/", "/cart"})
    public String cart() {
        return "client/content/basket/cart.html";
    }

}
