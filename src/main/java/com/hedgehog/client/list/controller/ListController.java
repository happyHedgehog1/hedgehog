package com.hedgehog.client.list.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class ListController {



    @GetMapping("/category")
    public String getCategory(){
        return "client/content/list/category";
    }


}
