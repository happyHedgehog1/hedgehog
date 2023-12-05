package com.hedgehog.client.agreement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agreement/*")
public class AgreementController {

    @GetMapping("/privacyPolicy")
    public String getPrivacyPolicy() {

        return "client/content/agreement/privacyPolicy";
    }

    @GetMapping("/termsAndConditions")
    public String getTermsAndConditions() {

        return "client/content/agreement/termsAndConditions";
    }
}
