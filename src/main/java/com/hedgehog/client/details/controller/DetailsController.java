package com.hedgehog.client.details.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("details")
public class DetailsController {
    @GetMapping("cancelPaybackInfo")
        public String cancelPaybackInfo() {
        return "client/content/details/cancelPaybackInfo.html";
    }

    @GetMapping("noticeInfo")
    public String noticeInfo() {
        return "client/content/details/noticeInfo.html";
    }
    @GetMapping("orderDetails")
    public String orderDetails() {
        return "client/content/details/orderDetails.html";
    }
    @GetMapping("questionInfo")
    public String questionInfo() {
        return "client/content/details/questionInfo.html";
    }
    @GetMapping("reviewInfo.html")
    public String reviewInfo() {
        return "client/content/details/reviewInfo.html";
    }

}
