package com.hedgehog.client.board.controller;

import com.hedgehog.client.board.model.dto.QuestionDTO;
import com.hedgehog.client.board.model.service.BoardService;
import com.hedgehog.common.paging.SelectCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hedgehog.common.paging.Pagenation;

@Controller
@RequestMapping("/board/*")
@Slf4j
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /*
     * 게시글 그 자체
     * */
    @GetMapping("/board")
    public String getBoard() {

        return "client/content/board/board";
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
        /*한 페이지에 10개*/
        int limit = 10;
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

        log.info("questionList : BoardController... questionList : "+questionList);
        mv.addObject("questionList",questionList);
        mv.addObject("selectCriteria",selectCriteria);
        log.info("questionList : BoardController... selectCriteria"+selectCriteria);
        mv.setViewName("/client/content/board/questionList");

        log.info("questionList : BoardController..... end..");
        return mv;
    }

    @GetMapping("/reviewList")
    public String reviewList() {
        return "/client/content/board/reviewList";
    }

    @GetMapping("/noticeList")
    public String noticeList() {
        return "/client/content/board/noticeList";
    }


}
