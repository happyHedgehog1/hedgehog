package com.hedgehog.client.myshop.controller;

import com.hedgehog.client.myshop.model.service.WithdrawalReasonService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/myshop/withdrawalReason")
public class WithdrawalReasonController {
    private final WithdrawalReasonService withdrawalReasonService;

    public WithdrawalReasonController(WithdrawalReasonService withdrawalReasonService) {
        this.withdrawalReasonService = withdrawalReasonService;
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println("이거 나오냐");
        try {
            String imagePath = withdrawalReasonService.uploadImage(file);
            return imagePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
