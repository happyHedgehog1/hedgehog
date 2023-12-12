package com.hedgehog.admin.adminMember.controller;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.service.AdminMemberServiceImpl;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
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

    @GetMapping(value = "/memberDetail")
    private String orderDetail(@RequestParam int member_code, Model model){
        log.info("");
        log.info("");
        log.info("selectProductDetail~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~member_code : {}", member_code);

        AdminAllMemberDTO memberDetail = adminMemberServiceimpl.memberDetail(member_code);
        log.info("~~~~~~~~~~~~~~~~memberDetail : {}", memberDetail);


        model.addAttribute("memberDetail", memberDetail);
        return "admin/content/member/memberDetail";

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

