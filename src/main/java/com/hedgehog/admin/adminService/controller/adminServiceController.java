package com.hedgehog.admin.adminService.controller;

import com.hedgehog.admin.adminService.model.dto.*;
import com.hedgehog.admin.adminService.model.service.AdminFAQServiceImpl;
import com.hedgehog.admin.adminService.model.service.AdminInquiryServiceImpl;
import com.hedgehog.admin.adminService.model.service.AdminReviewServiceImpl;
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
    private final AdminFAQServiceImpl adminFAQServiceImpl;
    private final AdminReviewServiceImpl adminReviewServiceImpl;


    public adminServiceController(AdminInquiryServiceImpl adminInquiryServiceImpl, AdminReviewServiceImpl adminReviewServiceImpl, AdminFAQServiceImpl adminFAQServiceImpl) {
        this.adminInquiryServiceImpl = adminInquiryServiceImpl;
        this.adminReviewServiceImpl = adminReviewServiceImpl;
        this.adminFAQServiceImpl = adminFAQServiceImpl;

    }



    //상품문의 첫화면
    @GetMapping("/productInquiryPage")
    public String productInquiryPage () {
        return "admin/content/Service/Product-inquiry";
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





    //상품리뷰
    @GetMapping("/Product-review")
    public ModelAndView productReview(@ModelAttribute AdminReviewForm form) {
        log.info("review============= start");
        log.info(form.toString());

        List<AdminReviewDTO> reviewList = adminReviewServiceImpl.searchReview(form);
        log.info("reviewList=============" + reviewList);

        int totalResult = reviewList.size();

        ModelAndView modelAndView = new ModelAndView("admin/content/Service/Product-review");
        modelAndView.addObject("reviewList", reviewList);
        modelAndView.addObject("totalResult", totalResult);


        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }

    //FAQ 첫화면
    @GetMapping("/FAQPage")
    public String FAQ () {
        return "admin/content/Service/FAQ";
    }
    //FAQ
    @GetMapping("/FAQ")
    public ModelAndView FAQ(@ModelAttribute AdminFAQForm form){
        log.info("FAQ============= start");
        log.info(form.toString());

        List<AdminFAQDTO> FAQList = adminFAQServiceImpl.searchFAQ(form);
        log.info("FAQList=============" + FAQList);

        int totalResult = FAQList.size();

        ModelAndView modelAndView = new ModelAndView("admin/content/Service/FAQ");
        modelAndView.addObject("FAQList", FAQList);
        modelAndView.addObject("totalResult", totalResult);


        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }
    //공지사항 첫화면
    @GetMapping("/noticePage")
    public String notice () {
        return "admin/content/Service/notice";
    }
    //공지사항
    @GetMapping(value = "/notice")
    public ModelAndView notice(@ModelAttribute AdminFAQForm form) {
        log.info("notice============= start");
        log.info(form.toString());

        List<AdminFAQDTO> noticeList = adminFAQServiceImpl.searchNotice(form);
        log.info("noticeList=============" + noticeList);

        int totalResult = noticeList.size();
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < noticeList.size(); i++) {
            String state = noticeList.get(i).getState();
            log.info(state);

            if (state.equals("Y")) {
                countY++;

            }
            if (state.equals("N")) {
                countN++;
            }
        }


        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);

        ModelAndView modelAndView = new ModelAndView("admin/content/Service/notice");
        modelAndView.addObject("noticeList", noticeList);
        modelAndView.addObject("totalResult", totalResult);
        modelAndView.addObject("countY", countY);
        modelAndView.addObject("countN", countN);


        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }













    @GetMapping("/email")
        public String email () {
            return "admin/content/Service/email";
        }

        @GetMapping("/emailHistory")
        public String emailHistory () {
            return "admin/content/Service/emailHistory";
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
