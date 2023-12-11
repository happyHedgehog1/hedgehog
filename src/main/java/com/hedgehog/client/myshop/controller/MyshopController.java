package com.hedgehog.client.myshop.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.auth.model.dto.MemberDTO;
import com.hedgehog.client.myshop.model.service.MyshopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/myshop")
@Slf4j
public class MyshopController {
    private final MyshopService myshopService;

    public MyshopController(MyshopService myshopService) {
        this.myshopService = myshopService;
    }

    @GetMapping("/mypage")
    public ModelAndView mypage(@AuthenticationPrincipal LoginDetails loginDetails, ModelAndView mv) {
        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
        mv.addObject("name", loginUserDTO.getName());

        int point = myshopService.getMyPoint(loginUserDTO.getUserCode());
        mv.addObject("point", point);
        mv.setViewName("/client/content/myshop/mypage");
        return mv;
    }

    @GetMapping("/info")
    public ModelAndView memberInfo(@AuthenticationPrincipal LoginDetails loginDetails, ModelAndView mv) {
        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
        // userCode를 가져온 다음에 member를 읽어온다.
        MemberDTO member = myshopService.getMemberInfo(loginUserDTO.getUserCode());
        mv.addObject("name", loginUserDTO.getName());
        mv.addObject("birthday", member.getBirthday());
        mv.addObject("gender", member.getGender());
        mv.addObject("userId", loginUserDTO.getUserId());
        mv.addObject("email", member.getEmail());
        mv.addObject("emailService", member.getEmailService());
        mv.addObject("phone", member.getPhone());
        mv.setViewName("/client/content/myshop/info");
        return mv;
    }

    @GetMapping("/modifyInfo")
    public String infoChange() {
        return "/client/content/myshop/modifyInfo";
    }

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
