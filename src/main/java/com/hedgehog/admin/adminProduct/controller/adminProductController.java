package com.hedgehog.admin.adminProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class adminProductController {

    @GetMapping("/productserach")
    public String productsearch(){ return "admin/content/product/productSerch.html";}


    @GetMapping("/productAdd")
    public String productadd(){ return "admin/content/product/productAdd.html";}



}
