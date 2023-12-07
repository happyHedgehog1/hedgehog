package com.hedgehog.admin.adminMember.controller;

import com.hedgehog.admin.adminMember.model.dto.adminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.service.adminMemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
@Slf4j
public class adminMemberController {

    private final adminMemberServiceImpl adminMemberServiceimpl;

    public adminMemberController(adminMemberServiceImpl adminMemberService) {
        this.adminMemberServiceimpl = adminMemberService;
    }


    /**
     *
     * @return 회원조회 페이지 연결 메소드
     */
    @GetMapping("/membersearch")
    public ModelAndView memberList(ModelAndView mv)
    {
        List<adminAllMemberDTO> memberList = adminMemberServiceimpl.selectAllMemberList();
        log.info(memberList.toString());

        mv.addObject("memberList", memberList);
        mv.setViewName("admin/content/member/membersearch");
        return mv;
    }


    /**
     *
     * @return 탈퇴 회원 조회 페이지 연결 메소드
     */
    @GetMapping("/unregister")
    public String unregisterList(){
        return "admin/content/member/unregister.html";
    }


}
