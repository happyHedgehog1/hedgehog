package com.hedgehog.admin.adminMember.controller;

import com.hedgehog.admin.adminMember.model.dto.*;
import com.hedgehog.admin.adminMember.model.service.AdminMemberServiceImpl;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.admin.exception.OrderStateUpdateException;
import com.hedgehog.admin.exception.UnregistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class AdminMemberController {

    private final AdminMemberServiceImpl adminMemberServiceimpl;

    public AdminMemberController(AdminMemberServiceImpl adminMemberService) {
        this.adminMemberServiceimpl = adminMemberService;
    }
    @PostMapping(value = "/sendMail")
    private String sendMail(@RequestParam(name = "memberId") List<String> memberId,
                            @ModelAttribute AdminSendMailDTO mailDTO,
                            RedirectAttributes redirectAttributes) {
        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~memberList : {}", memberId);
        log.info("~~~~~~~~~~~~~~~~mailDTO : {}", mailDTO);

        // Flash 속성에서 memberId를 받아옴
        List<String> name = (List<String>) redirectAttributes.getFlashAttributes().get("memberId");
        log.info("Received memberId in sendMail: {}", name);

        return "admin/content/member/sendMail";
    }


    @PostMapping(value = "/selectMemberSendMailPage")
    private String selectMemberSendMailPage(@RequestParam("resultCheckbox")List<String> memberId,
                                            Model model,
                                            RedirectAttributes redirectAttributes){
        log.info("");
        log.info("");
        log.info("selectMemberSendMailPage~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~memberId : {}", memberId);
        AdminSendMailDTO sendMailDTO = adminMemberServiceimpl.selectMemberSendMailPage(7);

        redirectAttributes.addFlashAttribute("memberId", memberId);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/content/member/sendMail");
        mv.addObject("memberId", memberId);
        mv.addObject("sendMailDTO", sendMailDTO);

        model.addAttribute(sendMailDTO);
        model.addAttribute(memberId);


        return "admin/content/member/sendMail";

    }



    @GetMapping(value = "/pointAdd")
    private String pointAdd(@RequestParam("memberCode") int memberCode,
                            @RequestParam("point") int point) throws UnregistException {
        log.info("*********************** pointAdd");
        log.info("*********************** memberId"+memberCode);
        log.info("*********************** point"+point);

        AdminMemberDTO memberDTO = new AdminMemberDTO();
        memberDTO.setMember_code(memberCode);
        memberDTO.setPoint(point);

        adminMemberServiceimpl.pointAdd(memberDTO);

        return "redirect:/member/pointPage?member_code=" + memberCode;
    }

    @GetMapping (value = "/pointPage")
    private String pointPage(@RequestParam int member_code,
                            Model model){

        log.info("*********************** memberWithdraw");
        log.info("*********************** memberId"+member_code);

        AdminAllMemberDTO memberDetail = adminMemberServiceimpl.memberDetail(member_code);


            log.info("*******************memberDetail :" + memberDetail);
        model.addAttribute("memberDetail", memberDetail);



        return "admin/content/member/pointPage";
    }




    /**
     * 회원조회 페이지에서 회원 탈퇴 시키는 메소드
     * @param memberId
     * @param rttr
     * @return
     * @throws OrderStateUpdateException
     * @throws UnregistException
     */
    @PostMapping(value = "/memberWithdraw")
    private String memberWithdraw(@RequestParam("resultCheckbox")List<String> memberId,
                                  RedirectAttributes rttr) throws UnregistException {

        log.info("*********************** memberWithdraw");
        log.info("*********************** memberId"+memberId);



        for(int i =0; i < memberId.size(); i++){

            log.info("memberId**********************" + memberId.get(i));
            int memberCode = Integer.parseInt(memberId.get(i));
            AdminAllMemberDTO adminAllMemberDTO = new AdminAllMemberDTO();
            adminAllMemberDTO.setMember_code(memberCode);

            log.info("*******************adminAllMemberDTO :" + adminAllMemberDTO);
            adminMemberServiceimpl.memberWithdraw(adminAllMemberDTO);

        }

        rttr.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "redirect:/member/membersearchPage";
    }



    /**
     * @return 회원조회 페이지 연결 메소드
     */

    @GetMapping("/membersearch")
    public ModelAndView membersearch(@ModelAttribute AdminMemberForm form) {
        log.info("membersearch========start=============");
        log.info(form.toString());

        List<AdminAllMemberDTO> memberList = adminMemberServiceimpl.selectMember(form);
        log.info("memberList=====================" + memberList);

        int totalResult = memberList.size();


        ModelAndView modelAndView = new ModelAndView("admin/content/member/membersearch");
        modelAndView.addObject("memberList", memberList);
        modelAndView.addObject("totalResult", totalResult);


        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }

    @GetMapping("/membersearchPage")
    public String memberList()
    {
        return "admin/content/member/membersearch";
    }


        /**
         *
         * @return 탈퇴 회원 조회 페이지 연결 메소드
         */
        @GetMapping("/unregister")
        public String unregisterList () {
            return "admin/content/member/unregister";
        }


    }

