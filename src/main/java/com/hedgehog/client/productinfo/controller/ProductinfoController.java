package com.hedgehog.client.productinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductinfoController {

    @GetMapping("/product")
    public String getProduct(){

        return "client/content/productinfo/product";
    }
}
