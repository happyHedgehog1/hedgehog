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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class AdminMemberController {

    private final AdminMemberServiceImpl adminMemberServiceimpl;

    public AdminMemberController(AdminMemberServiceImpl adminMemberService) {
        this.adminMemberServiceimpl = adminMemberService;
    }

    /**
     * 메일 양식을 가져와서 화면에 띄우는 메소드
     * @param memberCode
     * @return
     */
    @PostMapping(value = "/selectMemberSendMailPage")
    private ModelAndView selectMemberSendMailPage(@RequestParam("resultCheckbox")List<String> memberCode){
        log.info("");
        log.info("");
        log.info("selectMemberSendMailPage~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~memberId : {}", memberCode);

//        sendMailDTO.setMemberId(new ArrayList<>(memberCode));

        AdminSendMailDTO sendMailDTO = adminMemberServiceimpl.selectMemberSendMailPage(7);

        sendMailDTO.setMemberId(new ArrayList<>(memberCode));

        log.info("~~~~~~~~~~~~~~~~sendMailDTO : {}", sendMailDTO);

        ModelAndView mv = new ModelAndView();
        mv.addObject("sendMailDTO", sendMailDTO);
        mv.setViewName("admin/content/member/sendMail");

        log.info(mv.getModel().toString());


        return mv;

    }

    /**
     * 메일 발송하는 메소드
     * @param mailDTO
     * @param rttr
     * @return
     * @throws UnregistException
     */
    @PostMapping(value = "/sendMail")
    private String sendMail(@ModelAttribute AdminSendMailDTO mailDTO
                            ,RedirectAttributes rttr
                             ) throws UnregistException {
        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~mailDTO : {}", mailDTO);


//        mailDTO.setMemberId(name);
//        for(int i = 0; i < mailDTO.getMemberId().size(); i++){
//            String memberId = mailDTO.getMemberId().get(i);
//            mailDTO.setMemberId(memberId);
//        }
//메일 보내는 메소드
        adminMemberServiceimpl.sendMail(mailDTO);
        rttr.addFlashAttribute("message", "메일 전송이 성공하였습니다.");

//        메일 히스토리 테이블에 등록하는 메소드 Transactional때매 따로 생성
//        adminMemberServiceimpl.insertMailHistoryTable(mailDTO);



        return "admin/content/member/blank";
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
     * 회원 탈퇴하면 user 테이블의 withdraw_state를 Y로 변경하고,
     * withdraw 테이블에 해당 member insert한다
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

        rttr.addFlashAttribute("message", "회원탈퇴 신청되었습니다. 탈퇴 확정은 탈퇴회원조회 페이지에서 진행해주세요.");
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

