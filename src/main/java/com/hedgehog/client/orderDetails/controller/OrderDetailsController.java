package com.hedgehog.client.orderDetails.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/myshop")
@Slf4j
public class OrderDetailsController {

    @GetMapping("/exchangePaybackInfo")
    public String exchangePaybackInfo(@AuthenticationPrincipal LoginDetails loginDetails,
                                    @RequestParam String state) {
        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
        /*
        * state는 상태.
        * 전체보기, 결제완료, 배송완료, 교환, 환불,
        * */
        return "exchangePaybackInfo";
    }


    @GetMapping("/orderDeliveryInfo")
    public String orderDeliveryInfo() {
        return "/client/content/myshop/orderDeliveryInfo";
    }

    @GetMapping("/orderDetails")
    public String orderDetails() {
        return "/client/content/myshop/orderDetails";
    }
}
