package com.hedgehog.client.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myshop")
public class MyshopController {

    @GetMapping("/mypage")
    public String mypage() {
        return "/client/content/myshop/mypageMember";
    }

    @GetMapping("/memberInfoChange")
    public String infoChange() {
        return "/client/content/myshop/memberInfoChange";
    }

    @GetMapping("/info")
    public String memberInfo() {
        return "/client/content/myshop/memberInfo";
    }

//    details에서 Myshop으로 옮긴것들
    @GetMapping("/cancelPaybackInfo")
    public String cancelPaybackInfo() {
        return "/client/content/myshop/cancelPaybackInfo";
    }
    @GetMapping("/orderDetails")
    public String orderDetails() {
        return "/client/content/myshop/orderDetails";
    }
    @GetMapping("/orderDeliveryinfo")
    public String orderDeliveryinfo() {
        return "/client/content/myshop/orderDeliveryinfo";
    }

    @GetMapping("/withdrawalReason")
    public String withdrawalReason() {
        return "/client/content/myshop/withdrawalReason";
    }



    }
