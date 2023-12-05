package com.hedgehog.admin.adminservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autoMailViewport")
public class autoMailWriteController {

    @GetMapping("/answer")
    public String answer(){
        return "admin/content/Service/autoMail/viewport/answerViewport.html";
    }

    @GetMapping("/dormantMember")
    public String dormantMember(){
        return "admin/content/Service/autoMail/viewport/dormantMemberViewport.html";
    }

    @GetMapping("/mailModify")
    public String mailModify(){
        return "admin/content/Service/autoMail/viewport/mailViewport.html";
    }
    @GetMapping("/newMember")
    public String newMember(){
        return "admin/content/Service/autoMail/viewport/newMemberViewport.html";
    }
    @GetMapping("/orderList.html")
    public String orderList(){
        return "admin/content/Service/autoMail/viewport/orderListViewport.html";
    }
    @GetMapping("/passwordModify")
    public String passwordModify(){
        return "admin/content/Service/autoMail/viewport/passwordViewport.html";
    }
    @GetMapping("/pointModify")
    public String pointModify(){
        return "admin/content/Service/autoMail/viewport/pointModify.html";
    }
    @GetMapping("/unregister")
    public String unregister(){
        return "admin/content/Service/autoMail/viewport/unregisterModify.html";
    }
}
