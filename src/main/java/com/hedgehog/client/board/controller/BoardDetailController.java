package com.hedgehog.client.board.controller;

import com.hedgehog.client.board.model.dto.FaqDTO;
import com.hedgehog.client.board.model.dto.NoticeDTO;
import com.hedgehog.client.board.model.dto.QuestionDTO;
import com.hedgehog.client.board.model.dto.ReviewDTO;
import com.hedgehog.client.board.model.service.BoardDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board/*")
@Slf4j
@AllArgsConstructor
public class BoardDetailController {
    private final BoardDetailService boardDetailService;

    /*
     * 게시글 그 자체
     * */
    @GetMapping("detail")
    public ModelAndView getBoardDetail(/*@AuthenticationPrincipal LoginDetails loginDetails,*/
            @RequestParam int postType,
            @RequestParam int postCode,
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchValue,
            @RequestParam(defaultValue = "gradeDESC", required = false) String orderBy,
            @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
            ModelAndView mv) {
        /*
         * postType
         * 1 리뷰게시판   tbl_review -> searchCondition, searchValue, orderBy, pageNo
         * 2 문의게시판   tbl_inquiry -> searchCondition, searchValue, pageNo
         * 3 공지사항    tbl_admin_bulletin_board -> orderBy, pageNo
         * 4 FAQ        tbl_admin_bulletin_board -> orderBy, pageNo
         *
         * postCode의 경우 postType과 조합되면 겹칠일이 없을 것이다.
         * */

        /*
         * 계정 권한같은 경우. 여기에 접속했을때. 현재 계정의 정보를 확인한다.
         * @AuthenticationPrincipal -> 이용해서.
         * 만약 null이면 로그인한 상태가 아니므로 비밀글에는 접근하지 못한다.
         * 만약 이 권한이 ADMIN 이나 SUPERADMIN 이면 접근이 그냥 가능하다.
         * 만약 이 권한이 MEMBER 이면. 현재 로그인한 user_code와 그 글의 user_code. 그리고 비밀글 여부를 가져온다.
         * >>>> 비밀글이면서 user_code가 동일하면 접속이 가능하다.
         * >>>> 비밀글이면서 user_code가 다르면 접속 불가능
         * >>>> 비밀글이 아니면 누구나 접속가능.
         *
         * --- 일단 비밀글은 고려하지 말자.
         * */
        log.info("BoardDetailController >> getBoardDetail >> postType : " + postType);
        log.info("BoardDetailController >> getBoardDetail >> postCode : " + postCode);
        mv.addObject("postType", postType);
        if (postType == 1) {
            ReviewDTO reviewDTO = boardDetailService.getReviewDetail(postCode);
            mv.addObject("board", reviewDTO);
            log.info("BoardDetailController >> getBoardDetail >> reviewDTO : " + reviewDTO);
        } else if (postType == 2) {
            QuestionDTO questionDTO = boardDetailService.getQuestionDetail(postCode);
            mv.addObject("board", questionDTO);
            log.info("BoardDetailController >> getBoardDetail >> questionDTO : " + questionDTO);
        } else if (postType == 3) {
            boardDetailService.addViews(postCode);
            NoticeDTO noticeDTO = boardDetailService.getNoticeDetail(postCode);
            mv.addObject("board", noticeDTO);
            log.info("BoardDetailController >> getBoardDetail >> noticeDTO : " + noticeDTO);
        } else if (postType == 4) {
            boardDetailService.addViews(postCode);
            FaqDTO faqDTO = boardDetailService.getFaqDetail(postCode);
            mv.addObject("board", faqDTO);
            log.info("BoardDetailController >> getBoardDetail >> faqDTO : " + faqDTO);
        }


        mv.setViewName("client/content/board/boardDetail");
        return mv; // postType은 확실히 반환받는다. 이 반환받는거에 따라 사용하는 Detail 시리즈 속성이 달라진다.
    }

//    @PostMapping("/detail/delete")
//    public ModelAndView deleteBoardDetail(@RequestParam int userCode,
//                                          @RequestParam int postType,
//                                          @RequestParam int postCode,
//                                          ModelAndView mv) {
//        log.info("userCode = " + userCode);
//        log.info("postType = " + postType);
//        log.info("postCode = " + postCode);
//        mv.setViewName("/");
//        return mv;
//    }
}
