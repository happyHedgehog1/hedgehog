package com.hedgehog.client.agreement.controller;

import com.hedgehog.client.agreement.model.service.AgreementService;
import com.hedgehog.client.auth.model.dto.PostDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/agreement/*")
@AllArgsConstructor
public class AgreementController {
    private final AgreementService agreementService;

    @GetMapping("/privacyPolicy")
    public ModelAndView getPrivacyPolicy(ModelAndView mv) {
        PostDTO post = agreementService.getPrivacyPolicy();
        mv.addObject("post",post);
        System.out.println(post);
        mv.setViewName("client/content/agreement/privacyPolicy");
        return mv;
    }

    @GetMapping("/termsAndConditions")
    public ModelAndView getTermsAndConditions(ModelAndView mv) {
        PostDTO post = agreementService.getTermsAndConditions();
        mv.addObject("post",post);
        System.out.println(post);
        mv.setViewName("client/content/agreement/termsAndConditions");
        return mv;
    }
}
