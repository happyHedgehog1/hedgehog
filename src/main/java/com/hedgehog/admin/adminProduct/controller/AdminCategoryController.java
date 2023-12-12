package com.hedgehog.admin.adminProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class AdminCategoryController {

    /**
     * 상분 분류 페이지 연결 메소드
     * @return
     */
    @GetMapping("/categoryAdd")
    public String categoryadd(){ return "admin/content/product/categoryAdd.html";}

}
