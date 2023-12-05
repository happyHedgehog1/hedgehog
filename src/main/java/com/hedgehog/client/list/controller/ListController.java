package com.hedgehog.client.list.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/list/*")
@Slf4j
public class ListController {

//    @GetMapping("category")
//    public String getCategoryLocation(@RequestParam String type, Model model) {
//
//        log.info("[ListController] getCategoryLocation type : {}", type);
//
//        String subject1 = "";
//        String subject2 = "";
//
//        switch (type) {
//            case "bed": subject1 = "침실";
//                        subject2 = "침대";
//                        break;
//        }
//
//        model.addAttribute("subject1", subject1);
//        model.addAttribute("subject2", subject2);
//
//
//
//        return "category";
//
//    }
    // 데이터베이스에서 필요정보를 객체로 받아서 결과페이지로 넘겨준다.

    @GetMapping("/category")
    public String getBedRoodBed(){
        return "/client/content/list/category";
    }


}
