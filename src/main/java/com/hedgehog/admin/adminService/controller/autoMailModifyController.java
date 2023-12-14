package com.hedgehog.admin.adminService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autoMailModify")
public class autoMailModifyController {
    @GetMapping("/answer")
    public String answer(){
        return "admin/content/Service/autoMail/modify/answerModify.html";
    }

    @GetMapping("/dormantMember")
    public String dormantMember(){
        return "admin/content/Service/autoMail/modify/dormantMemberModify.html";
    }

    @GetMapping("/mailModify")
    public String mailModify(){
        return "admin/content/Service/autoMail/modify/mailModify.html";
    }
    @GetMapping("/newMember")
    public String newMember(){
        return "admin/content/Service/autoMail/modify/newMemberModify.html";
    }
    @GetMapping("/orderList")
    public String orderList(){
        return "admin/content/Service/autoMail/modify/orderListModify.html";
    }
    @GetMapping("/passwordModify")
    public String passwordModify(){
        return "admin/content/Service/autoMail/modify/passwordModify.html";
    }
    @GetMapping("/pointModify")
    public String pointModify(){
        return "admin/content/Service/autoMail/modify/pointModify.html";
    }
    @GetMapping("/unregister")
    public String unregister(){
        return "admin/content/Service/autoMail/modify/unregisterModify.html";
    }

}
