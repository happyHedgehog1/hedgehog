package com.hedgehog.admin.adminProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class AdminCategoryController {


    @GetMapping("/categoryAdd")
    public String categoryadd(){ return "admin/content/product/categoryAdd.html";}

}
