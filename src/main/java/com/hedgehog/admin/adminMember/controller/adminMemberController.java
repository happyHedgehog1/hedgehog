package com.hedgehog.admin.adminMember.controller;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import com.hedgehog.admin.adminMember.model.service.adminMemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class adminMemberController {

    private final adminMemberServiceImpl adminMemberServiceimpl;

    public adminMemberController(adminMemberServiceImpl adminMemberService) {
        this.adminMemberServiceimpl = adminMemberService;
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
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < memberList.size(); i++) {
            String email_consent = memberList.get(i).getEmail_consent();
            if ("Y".equals(String.valueOf(email_consent))) {
                countY++;
            }
            if ("N".equals(String.valueOf(email_consent))) {
                countN++;
            }
        }
        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);
        ModelAndView modelAndView = new ModelAndView("admin/content/member/membersearch");
        modelAndView.addObject("memberList", memberList);
        modelAndView.addObject("totalResult", totalResult);
        modelAndView.addObject("countY", countY);
        modelAndView.addObject("countN", countN);

        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }

//    @GetMapping("/membersearch")
//    public ModelAndView memberList(ModelAndView mv)
//    {
//        List<adminAllMemberDTO> memberList = adminMemberServiceimpl.selectAllMemberList();
//        log.info(memberList.toString());
//
//        mv.addObject("memberList", memberList);
//        mv.setViewName("admin/content/member/membersearch");
//        return mv;
//    }


        /**
         *
         * @return 탈퇴 회원 조회 페이지 연결 메소드
         */
        @GetMapping("/unregister")
        public String unregisterList () {
            return "admin/content/member/unregister.html";
        }


    }

