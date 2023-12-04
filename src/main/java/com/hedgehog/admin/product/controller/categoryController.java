package com.hedgehog.admin.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class categoryController {


    @GetMapping("/categoryAdd")
    public String categoryadd(){ return "admin/content/product/categoryAdd.html";}

}
