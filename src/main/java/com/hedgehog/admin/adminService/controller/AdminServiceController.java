package com.hedgehog.admin.adminService.controller;

import com.hedgehog.admin.adminService.model.dto.*;
import com.hedgehog.admin.adminService.model.service.AdminCommentServiceImpl;
import com.hedgehog.admin.adminService.model.service.AdminFAQServiceImpl;
import com.hedgehog.admin.adminService.model.service.AdminInquiryServiceImpl;
import com.hedgehog.admin.adminService.model.service.AdminReviewServiceImpl;
import com.hedgehog.admin.exception.BoardException;
import com.hedgehog.client.product.model.service.ProductService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/Service")
@Slf4j
public class AdminServiceController {
    private final AdminInquiryServiceImpl adminInquiryServiceImpl;
    private final AdminFAQServiceImpl adminFAQServiceImpl;
    private final AdminReviewServiceImpl adminReviewServiceImpl;
    private final AdminCommentServiceImpl adminCommentServiceImpl;


    public AdminServiceController(AdminInquiryServiceImpl adminInquiryServiceImpl, AdminReviewServiceImpl adminReviewServiceImpl, AdminFAQServiceImpl adminFAQServiceImpl, AdminCommentServiceImpl adminCommentServiceImpl) {
        this.adminInquiryServiceImpl = adminInquiryServiceImpl;
        this.adminReviewServiceImpl = adminReviewServiceImpl;
        this.adminFAQServiceImpl = adminFAQServiceImpl;

        this.adminCommentServiceImpl = adminCommentServiceImpl;
    }

//    @ResponseBody
//    @RequestMapping(value="/uploadSummernoteImageFile",method=RequestMethod.POST)
//    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
//                                                HttpServletRequest request) {
//        JsonObject jsonObject = new JsonObject();
//        //파일저장 외부 경로, 파일명, 저장할 파일명
//        try {
//            String originalFileName = multipartFile.getOriginalFilename();
//            String root = request.getSession().getServletContext().getRealPath("resources");
//            String savePath = root + "\\image\review\summerImageFiles";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            String extension = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
//            String boardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
//            File targetFile = new File(savePath);
//            if(!targetFile.exists()) {
//                targetFile.mkdir();
//            }
//            multipartFile.transferTo(new File(savePath+"\\"+boardFileRename));
//            System.out.println(savePath);
//            jsonObject.addProperty("url","/resources/image/review/summerImageFiles/"+boardFileRename);
//            jsonObject.addProperty("originName",originalFileName);
//            jsonObject.addProperty("reponseCode","success");
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

    @PostMapping(value = "/noticeModify")
    public String noticeModify(@ModelAttribute AdminFAQDTO adminFAQDTO,
                            Model model){
        log.info("*********************** FAQModify");
        log.info("*********************** adminFAQDTO"+adminFAQDTO);

        adminFAQServiceImpl.FAQModify(adminFAQDTO);
        return "admin/content/Service/notice";
    }
    @PostMapping(value = "/FAQModify")
    public String FAQModify(@ModelAttribute AdminFAQDTO adminFAQDTO,
                            Model model){
        log.info("*********************** FAQModify");
        log.info("*********************** adminFAQDTO"+adminFAQDTO);

        adminFAQServiceImpl.FAQModify(adminFAQDTO);
        return "admin/content/Service/FAQ";
    }


    @GetMapping("/noticeModifyPage")
    public String noticeModifyPage(@RequestParam("postCode") int postCode, Model model){
        log.info("*********************** FAQModify");
        log.info("*********************** postCode"+postCode);

        AdminFAQDTO adminFAQDTO = adminFAQServiceImpl.FAQModifyPage(postCode);


        log.info("*******************adminReviewDTO :" + adminFAQDTO);
        model.addAttribute("adminNoticeDTO", adminFAQDTO);



        return "admin/content/Service/noticeModify";
    }

    @GetMapping("/FAQModifyPage")
    public String FAQModifyPage(@RequestParam("postCode") int postCode, Model model){
        log.info("*********************** FAQModify");
        log.info("*********************** postCode"+postCode);

        AdminFAQDTO adminFAQDTO = adminFAQServiceImpl.FAQModifyPage(postCode);


        log.info("*******************adminReviewDTO :" + adminFAQDTO);
        model.addAttribute("adminNoticeDTO", adminFAQDTO);



        return "admin/content/Service/FAQModify";
    }

    //상품문의 상세보기
    @GetMapping("/inquiryDetail")
    public String InquiryDetail(@RequestParam("inquiry_code")int inquiry_code,
                                @RequestParam("answer_state") String answer_state, Model model) {

        log.info("*********************** inquiryDetail");
        log.info("*********************** inquiry_code"+inquiry_code);



        AdminInquiryDTO adminInquiryDTO = adminInquiryServiceImpl.inquiryDetail(inquiry_code);

        log.info("===========================adminInquiryDTO" + adminInquiryDTO);
        model.addAttribute("adminInquiryDTO", adminInquiryDTO);

        return "admin/content/Service/Product-inquiry-details";}



//    //상품문의 답변
//    @GetMapping("/inquiryComment")
//    public String inquiryComment(@RequestParam ("inquiry_code")int inquiry_code,Model model){
//        AdminCommentDTO adminCommentDTO = adminInquiryServiceImpl.inquiryComment(inquiry_code);
//        log.info("===========================adminInquiryDTO" + adminCommentDTO);
//        model.addAttribute("adminInquiryDTO", adminCommentDTO);
//        return   "admin/content/Service/Product-inquiry-comment";
//    }
    //상품리뷰 상세보기
    @GetMapping("/reviewDetail")
    public String reviewDetail(@RequestParam("Review_code") int Review_code, Model model){
        log.info("*********************** reviewDetail");
        log.info("*********************** Review_code"+Review_code);

        AdminReviewDTO adminReviewDTO = adminReviewServiceImpl.reviewDetail(Review_code);


        log.info("*******************adminReviewDTO :" + adminReviewDTO);
        model.addAttribute("adminReviewDTO", adminReviewDTO);



        return "admin/content/Service/Product-review-details";
    }


    //상품문의 첫화면
    @GetMapping("/productInquiryPage")
    public String productInquiryPage() {
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

    //상품문의 상태 업데이트
    @PostMapping(value = "/inqStateUpdate")
    private String inqStateUpdate(@RequestParam("resultCheckbox") List<String> selectedInqCodes,
                                  @RequestParam("inqSelectCommit") String selectedInqState,
                                  RedirectAttributes rttr) throws BoardException {

        log.info("=================================inqStateUpdate");
        log.info("=================================selectedInqCodes" + selectedInqCodes);
        log.info("=================================selectedInqState" + selectedInqState);

        for (int i = 0; i < selectedInqCodes.size(); i++) {
            if ("on".equals(selectedInqCodes.get(i)) || selectedInqCodes.get(i).isEmpty()) {
                continue;
            } else {
                int inquiryCode = Integer.parseInt(selectedInqCodes.get(i));
                log.info("inquiryCode=================" + inquiryCode);
                AdminInquiryDTO inquiryDTO = new AdminInquiryDTO();
                inquiryDTO.setInquiry_code(inquiryCode);
                inquiryDTO.setState(selectedInqState);

                log.info("================inquiry" + inquiryDTO);
                adminInquiryServiceImpl.inqStateUpdate(inquiryDTO);
            }
        }
        rttr.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "admin/content/Service/Product-inquiry";
    }


    //상품리뷰 첫화면
    @GetMapping("/Product-reviewPage")
    public String productReview() {
        return "admin/content/Service/Product-review";
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

    @Autowired
    private ProductService productService;
    //상품리뷰 상태 업데이트

    @PostMapping(value = "/revStateUpdate")
    private String revStateUpdate(@RequestParam("resultCheckbox") List<String> selectedRevCodes,
                                  @RequestParam("revSelectCommit") String selectedRevState,
                                  RedirectAttributes rttr) throws BoardException {

        log.info("=================================revStateUpdate");
        log.info("=================================selectedRevCodes" + selectedRevCodes);
        log.info("=================================selectedRevState" + selectedRevState);

        for (int i = 0; i < selectedRevCodes.size(); i++) {
            if ("on".equals(selectedRevCodes.get(i)) || selectedRevCodes.get(i).isEmpty()) {
                continue;
            } else {
                int reviewCode = Integer.parseInt(selectedRevCodes.get(i));
                log.info("reviewCode=================" + reviewCode);
                AdminReviewDTO reviewDTO = new AdminReviewDTO();
                reviewDTO.setReview_code(reviewCode);
                reviewDTO.setState(selectedRevState);

                log.info("================review" + reviewDTO);
                adminReviewServiceImpl.revStateUpdate(reviewDTO);
            }
        }
        rttr.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "redirect:/Service/Product-review";
    }



    //FAQ 첫화면
    @GetMapping("/FAQPage")
    public String FAQ() {
        return "admin/content/Service/FAQ";
    }

    //FAQ
    @GetMapping("/FAQ")
    public ModelAndView FAQ(@ModelAttribute AdminFAQForm form) {
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

    //FAQ 상태 업데이트
    @PostMapping(value = "/FAQStateUpdate")
    private String FAQStateUpdate(@RequestParam("resultCheckbox") List<String> selectedFAQCodes,
                                  @RequestParam("FAQSelectCommit") String selectedFAQState,
                                  RedirectAttributes rttr) throws BoardException {

        log.info("=================================FAQStateUpdate");
        log.info("=================================selectedInqCodes" + selectedFAQCodes);
        log.info("=================================selectedInqState" + selectedFAQState);

        for (int i = 0; i < selectedFAQCodes.size(); i++) {
            if ("on".equals(selectedFAQCodes.get(i)) || selectedFAQCodes.get(i).isEmpty()) {
                continue;
            } else {
                int FAQCode = Integer.parseInt(selectedFAQCodes.get(i));
                log.info("FAQCode=================" + FAQCode);
                AdminFAQDTO FAQDTO = new AdminFAQDTO();
                FAQDTO.setPost_code(FAQCode);
                FAQDTO.setState(selectedFAQState);

                log.info("================FAQ" + FAQDTO);
                adminFAQServiceImpl.FAQStateUpdate(FAQDTO);

            }
        }
        rttr.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "redirect:/Service/FAQ";
    }

    //FAQ 등록
    @PostMapping("/FAQRegister")
    public String FAQRegister(@ModelAttribute AdminFAQDTO adminFAQDTO,
                                 Model model) throws BoardException {
        log.info("");
        log.info("");
        log.info("FAQ등록~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~adminFAQDTO : {}", adminFAQDTO);

        adminFAQServiceImpl.FAQRegister(adminFAQDTO);
        model.addAttribute(adminFAQDTO.getPost_code());

        return "admin/content/Service/FAQ";
    }


    //공지사항 첫화면
    @GetMapping("/noticePage")
    public String notice() {
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

    //공지사항 상태 업데이트
    @PostMapping(value = "/noticeStateUpdate")
    private String noticeStateUpdate(@RequestParam("resultCheckbox") List<String> selectedNoticeCodes,
                                  @RequestParam("noticeSelectCommit") String selectedNoticeState,
                                  RedirectAttributes rttr) throws BoardException {

        log.info("=================================noticeStateUpdate");
        log.info("=================================selectedNoticeCodes" + selectedNoticeCodes);
        log.info("=================================selectedNoticeState" + selectedNoticeState);

        for (int i = 0; i < selectedNoticeCodes.size(); i++) {
            if ("on".equals(selectedNoticeCodes.get(i)) || selectedNoticeCodes.get(i).isEmpty()) {
                continue;
            } else {
                int noticeCode = Integer.parseInt(selectedNoticeCodes.get(i));
                log.info("noticeCode=================" + noticeCode);
                AdminFAQDTO FAQDTO = new AdminFAQDTO();
                FAQDTO.setPost_code(noticeCode);
                FAQDTO.setState(selectedNoticeState);

                log.info("================FAQ" + FAQDTO);
                adminFAQServiceImpl.noticeStateUpdate(FAQDTO);

            }
        }
        rttr.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "redirect:/Service/notice";
    }
    //공지사항 등록
    @PostMapping("/noticeRegister")
    public String noticeRegister(@ModelAttribute AdminFAQDTO adminFAQDTO,
                                 Model model) throws  BoardException {
        log.info("");
        log.info("");
        log.info("공지사항등록~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~adminFAQDTO : {}", adminFAQDTO);

        adminFAQServiceImpl.noticeRegister(adminFAQDTO);
        model.addAttribute(adminFAQDTO.getPost_code());

        return "admin/content/Service/notice";
    }

    @GetMapping("/FAQWritePage")
    public String FAQWritePage() {
        return "admin/content/Service/FAQWrite";
    }
    //상품문의 답변
    @PostMapping("/inquiryComment")
    public String inquiryComment(@ModelAttribute AdminCommentDTO adminCommentDTO,
                                 @RequestParam("inquiry_code") int inquiry_code,
                                 @RequestParam("user_code") int user_code,
                                 @RequestParam("inqtitle") String inqtitle,
                                 @RequestParam("inqcontent") String inqcontent,
                                 Model model) throws BoardException, MessagingException, UnsupportedEncodingException {
        log.info("");
        log.info("");
        log.info("문의답변~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~adminCommentDTO : {}", adminCommentDTO);
        log.info("~~~~~~~~~~~~~~~~inqtitle : {}", inqtitle);
        log.info("~~~~~~~~~~~~~~~~inqcontent : {}", inqcontent);

        // adminCommentDTO에 inquiry_code 설정
        adminCommentDTO.setInquiry_code(inquiry_code);
        adminCommentDTO.setUser_code(user_code);
        adminCommentDTO.setInqtitle(inqtitle);
        adminCommentDTO.setInqcontent(inqcontent);

        log.info("~~~~~~~~~~~~~~~~adminCommentDTO : {}", adminCommentDTO);


        adminCommentServiceImpl.inquiryComment(adminCommentDTO);
        model.addAttribute("comment_code",adminCommentDTO.getComment_code());
        model.addAttribute("inquiry_code", inquiry_code);

        return "admin/content/Service/blank";
    }




    @GetMapping("/email")
    public String email() {
        return "admin/content/Service/email";
    }

    @GetMapping("/emailHistory")
    public String emailHistory() {
        return "admin/content/Service/emailHistory";
    }

    @GetMapping("/autoMail")
    public String autoMail() {
        return "admin/content/Service/autoMail";
    }

    @GetMapping("/noticeWritePage")
    public String noticeWritePage() {
        return "admin/content/Service/noticeWrite";
    }


    @GetMapping("/detail")
    public String productInquiryDetail() {
        return "admin/content/Service/Product-inquiry-details";
    }


}
