package com.hedgehog.client.list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class ListController {

    @GetMapping("/bed")
    public String bed(){
        return "client/content/list/mainCategoryBed.html";
    }
    @GetMapping("/desk")
    public String desk(){
        return "client/content/list/mainCategoryDesk.html";
    }
    @GetMapping("/sopa")
    public String sopa(){
        return "client/content/list/mainCategorySopa.html";
    }
    @GetMapping("/table")
    public String table(){
        return "client/content/list/mainCategoryTable.html";
    }



}
