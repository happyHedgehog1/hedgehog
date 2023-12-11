package com.hedgehog.admin.adminService.controller;

import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryForm;
import com.hedgehog.admin.adminService.model.service.AdminInquiryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/Service")
@Slf4j
public class adminServiceController {
    private final AdminInquiryServiceImpl adminInquiryServiceImpl;

    public adminServiceController(AdminInquiryServiceImpl adminInquiryServiceImpl) {
        this.adminInquiryServiceImpl = adminInquiryServiceImpl;
    }

    //상품문의
    @GetMapping(value = "/productInquiry")
    public ModelAndView productInquiry(@ModelAttribute AdminInquiryForm form) {
        log.info("productInquiry============= start");
        log.info(form.toString());

        List<AdminInquiryDTO> inquiryList = adminInquiryServiceImpl.searchInquiry(form);
        log.info("inquiryList=============" + inquiryList);

        int totalResult = inquiryList.size();
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < inquiryList.size(); i++) {
            String answer_state = inquiryList.get(i).getAnswer_state();
            log.info(answer_state);

            if (answer_state.equals("Y")) {
                countY++;

            }
            if (answer_state.equals("N")) {
                countN++;
            }
        }


        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);

        ModelAndView modelAndView = new ModelAndView("admin/content/Service/Product-inquiry");
        modelAndView.addObject("inquiryList", inquiryList);
        modelAndView.addObject("totalResult", totalResult);
        modelAndView.addObject("countY", countY);
        modelAndView.addObject("countN", countN);


        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }


    @GetMapping("/productInquiryPage")
    public String productInquiryPage () {
        return "admin/content/Service/Product-inquiry";
    }

        @GetMapping("/email")
        public String email () {
            return "admin/content/Service/email";
        }

        @GetMapping("/emailHistory")
        public String emailHistory () {
            return "admin/content/Service/emailHistory";
        }

        @GetMapping("/Product-review")
        public String productReview () {
            return "admin/content/Service/Product-review";
        }

        @GetMapping("/FAQ")
        public String FAQ () {
            return "admin/content/Service/FAQ";
        }

        @GetMapping("/notice")
        public String notice () {
            return "admin/content/Service/notice";
        }

        @GetMapping("/autoMail")
        public String autoMail () {
            return "admin/content/Service/autoMail";
        }

        @GetMapping("/noticeWrite")
        public String noticeWrite () {
            return "admin/content/Service/noticeWrite";
        }

        @GetMapping("/detail")
        public String productInquiryDetail () {
            return "admin/content/Service/Product-inquiry-details";
        }

    }
