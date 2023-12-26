package com.hedgehog.client.board.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.board.model.dto.*;
import com.hedgehog.client.board.model.service.BoardDetailService;
import com.hedgehog.client.board.model.service.BoardService;
import com.hedgehog.common.paging.SelectCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.hedgehog.common.paging.Pagenation;

@Controller
@RequestMapping("/board/*")
@Slf4j
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/reviewList")
    public ModelAndView reviewList(@RequestParam(required = false) String searchCondition,
                                   @RequestParam(required = false) String searchValue,
                                   @RequestParam(required = false) String orderBy,
                                   @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                   ModelAndView mv) {
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("orderBy", orderBy);
        int totalCount = boardService.selectTotalCountReviewList(searchMap);
        int limit = 3;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = null;
        if (searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                    searchCondition, searchValue, orderBy);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, orderBy);
        }
        List<ReviewDTO> reviewList = boardService.selectReviewList(selectCriteria);
        List<Integer> reviewCodes = reviewList.stream().map(ReviewDTO::getReviewCode).collect(Collectors.toList());
        List<PostImageDTO> imageList = boardService.getReviewImage(reviewCodes);
        mv.addObject("reviewList", reviewList);
        mv.addObject("imageList", imageList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.setViewName("/client/content/board/reviewList");
        return mv;
    }

    /*1:1 문의 게시판. -> tbl_inquiry*/
    @GetMapping("/questionList")
    public ModelAndView questionList(@RequestParam(required = false) String searchCondition,
                                     @RequestParam(required = false) String searchValue,
                                     @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                     ModelAndView mv) {
        /*searchCondition: 아이디, 제목, 내용, 제목+내용, 분류*/
        /*searchValue: wildcard로 검색하는 형태. 배송/제품/교환/환불 문의는 어떤식으로 할지 고민중. 단순히 wildcard가 제일 편하긴 하겠다만..*/
        /*쿼리스트링으로 표시되는건 ?currentPage=2 같은 형태. pageNo는 이 컨트롤러 메서드에서 활용되는 형태*/
        log.info("");
        log.info("");
        log.info("questionList : BoardController..... start");
        /*맨 먼저 목록보기를 누르면 1페이지가 나온다.*/
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        log.info("questionList : BoardController에서 검색조건은 현재 다음과 같음... : " + searchMap);

        /*우선 전체 게시물의 개수가 필요하다. 데이터베이스에서 먼저 전체 게시물 수를 조회해 온다.*/
        int totalCount = boardService.selectTotalCountQuestionList(searchMap);
        log.info("조건에 맞는 전체 문의 게시글의 수... : " + totalCount);
        /*한 페이지에 5개*/
        int limit = 5;
        /*한번에 페이징 버튼은 5개*/
        int buttonAmount = 5;
        /*페이징 처리용 로직을 위한 변수*/
        SelectCriteria selectCriteria = null;
        if (searchCondition != null && !"".equals(searchCondition)) {
            // 뭔가 검색조건이 있다면
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            // 검색조건이 없다면
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        log.info("");
        log.info("");
        log.info("questionList : BoardController..... selectCriteria : " + selectCriteria);

        List<QuestionDTO> questionList = boardService.selectQuestionList(selectCriteria);

        log.info("questionList : BoardController... questionList : " + questionList);
        mv.addObject("questionList", questionList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("questionList : BoardController... selectCriteria" + selectCriteria);
        mv.setViewName("/client/content/board/questionList");
        log.info("questionList : BoardController..... end..");
        return mv;
    }

    @GetMapping("/noticeList")
    public ModelAndView noticeList(@RequestParam(defaultValue = "writeDateDESC") String orderBy,
                                   @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                   ModelAndView mv) {
        /*orderBy:
         * writeDateDESC -> 최근 글부터
         * writeDateASC -> 옛날글부터
         * viewsDESC -> 조회수가 높은 순서대로
         * viewsASC -> 조회수가 낮은 순서대로*/
        log.info("");
        log.info("");
        log.info("noticeList : BoardController..... start");
        /*맨 먼저 목록보기를 누르면 1페이지가 나온다.*/
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("orderBy", orderBy);

        log.info("noticeList : BoardController에서 검색조건은 현재 다음과 같음... : " + searchMap);

        /*우선 전체 게시물의 개수가 필요하다. 데이터베이스에서 먼저 전체 게시물 수를 조회해 온다.*/
        int totalCount = boardService.selectTotalCountNoticeList(searchMap);
        log.info("조건에 맞는 전체 문의 게시글의 수... : " + totalCount);
        /*한 페이지에 5개*/
        int limit = 5;
        /*한번에 페이징 버튼은 5개*/
        int buttonAmount = 5;
        /*페이징 처리용 로직을 위한 변수*/
        SelectCriteria selectCriteria = null;
        if (orderBy != null) {
            // 뭔가 정렬기준이 있다면.
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, orderBy);
        } else {
            // 검색조건이 없다면
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        log.info("");
        log.info("");
        log.info("noticeList : BoardController..... selectCriteria : " + selectCriteria);

        List<NoticeDTO> noticeList = boardService.selectNoticeList(selectCriteria);

        log.info("noticeList : BoardController... noticeList : " + noticeList);
        mv.addObject("noticeList", noticeList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("noticeList : BoardController... selectCriteria" + selectCriteria);
        mv.setViewName("/client/content/board/noticeList");

        log.info("noticeList : BoardController..... end..");
        return mv;
    }

    @GetMapping("/faqList")
    public ModelAndView faqList(@RequestParam(defaultValue = "writeDateDESC") String orderBy,
                                @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                ModelAndView mv) {
        /*orderBy:
         * writeDateDESC -> 최근 글부터
         * writeDateASC -> 옛날글부터
         * viewsDESC -> 조회수가 높은 순서대로
         * viewsASC -> 조회수가 낮은 순서대로*/
        log.info("");
        log.info("");
        log.info("faqList : BoardController..... start");
        /*맨 먼저 목록보기를 누르면 1페이지가 나온다.*/
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("orderBy", orderBy);

        log.info("faqList : BoardController에서 검색조건은 현재 다음과 같음... : " + searchMap);

        /*우선 전체 게시물의 개수가 필요하다. 데이터베이스에서 먼저 전체 게시물 수를 조회해 온다.*/
        int totalCount = boardService.selectTotalCountFaqList(searchMap);
        log.info("조건에 맞는 전체 문의 게시글의 수... : " + totalCount);
        /*한 페이지에 5개*/
        int limit = 5;
        /*한번에 페이징 버튼은 5개*/
        int buttonAmount = 5;
        /*페이징 처리용 로직을 위한 변수*/
        SelectCriteria selectCriteria = null;
        if (orderBy != null) {
            // 뭔가 정렬기준이 있다면.
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, orderBy);
        } else {
            // 검색조건이 없다면
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        log.info("");
        log.info("");
        log.info("faqList : BoardController..... selectCriteria : " + selectCriteria);

        List<FaqDTO> faqList = boardService.selectFaqList(selectCriteria);

        log.info("faqList : BoardController... faqList : " + faqList);
        mv.addObject("faqList", faqList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("faqList : BoardController... selectCriteria" + selectCriteria);
        mv.setViewName("/client/content/board/faqList");

        log.info("faqList : BoardController..... end..");
        return mv;
    }
}
