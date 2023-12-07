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
    public ModelAndView membersearch(ModelAndView mv,
                                     @RequestParam(name = "date1", required = false) String date1,
                                     @RequestParam(name = "date2", required = false) String date2,
                                     @RequestParam(name = "memKeyword", required = false) String memKeyword,
                                     @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                                     @RequestParam(name = "memAgree", required = false) String memAgree) {

        // 필요한 경우, 여기서 입력 값의 유효성 검사 수행

        // 검색을 위한 매개변수 맵 구성
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("date1", date1);
        paramMap.put("date2", date2);
        paramMap.put("memKeyword", memKeyword);
        paramMap.put("searchKeyword", searchKeyword);
        paramMap.put("memAgree", memAgree);

        // 서비스에서 실제 검색 수행
        List<adminAllMemberDTO> memberList = adminMemberServiceimpl.searchMembers(paramMap);

        // 결과를 ModelAndView에 추가하고 뷰 설정
        mv.addObject("memberList", memberList);
        mv.setViewName("admin/content/member/membersearch");
        return mv;
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
    public String unregisterList(){
        return "admin/content/member/unregister.html";
    }


}
