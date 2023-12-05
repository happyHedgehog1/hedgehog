package com.hedgehog.admin.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Service")
public class serviceController {

    @GetMapping("/productInquiry")
    public String productInquiry(){
        return "admin/content/Service/Product-inquiry.html";
    }

    @GetMapping("/email")
    public String email(){
        return "admin/content/Service/email.html";
    }

    @GetMapping("/emailHistory")
    public String emailHistory(){
        return "admin/content/Service/emailHistory.html";
    }

    @GetMapping("/Product-review")
    public String productReview(){
        return "admin/content/Service/Product-review.html";
    }

    @GetMapping("/FAQ")
    public String FAQ(){
        return "admin/content/Service/FAQ.html";
    }

    @GetMapping("/notice")
    public String notice(){
        return "admin/content/Service/notice.html";
    }

    @GetMapping("/autoMail")
    public String autoMail(){return "admin/content/Service/autoMail.html";}

    @GetMapping("/noticeWrite")
    public String noticeWrite(){return "admin/content/Service/noticeWrite.html";}

    @GetMapping("/detail")
    public String productInquiryDetail(){
        return "adming/content/Service/Product-inquiry-details.html";
    }

}
