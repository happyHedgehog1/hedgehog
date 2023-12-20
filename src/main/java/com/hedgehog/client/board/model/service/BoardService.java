package com.hedgehog.client.board.model.service;

import com.hedgehog.client.board.model.dao.BoardMapper;
import com.hedgehog.client.board.model.dto.*;
import com.hedgehog.common.paging.SelectCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public int selectTotalCountQuestionList(Map<String, String> searchMap) {
        int result = mapper.selectTotalCountQuestionList(searchMap);
        log.info("");
        log.info("");
        log.info("BoardService : selectTotalCountQuestionList ... : " + result);

        return result;
    }

    public int selectTotalCountReviewList(Map<String, String> searchMap) {
        int result = mapper.selectTotalCountReviewList(searchMap);
        log.info("");
        log.info("");
        log.info("BoardService : selectTotalCountReviewList ... : " + result);

        return result;
    }

    public List<QuestionDTO> selectQuestionList(SelectCriteria selectCriteria) {
        List<QuestionDTO> result = mapper.selectQuestionList(selectCriteria);
        log.info("");
        log.info("");
        log.info("BoardService : selectQuestionList ... : " + result);

        return result;
    }

    public List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria) {
        List<ReviewDTO> result = mapper.selectReviewList(selectCriteria);
        log.info("");
        log.info("");
        log.info("BoardService : selectReviewList ... : " + result);

        return result;
    }

    public int selectTotalCountNoticeList(Map<String, String> searchMap) {
        int result = mapper.selectTotalCountNoticeList(searchMap);
        log.info("");
        log.info("");
        log.info("BoardService : selectTotalCountNoticeList ... : " + result);

        return result;
    }

    public List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria) {
        List<NoticeDTO> result = mapper.selectNoticeList(selectCriteria);
        log.info("");
        log.info("");
        log.info("BoardService : selectReviewList ... : " + result);

        return result;
    }

    public int selectTotalCountFaqList(Map<String, String> searchMap) {
        int result = mapper.selectTotalCountFaqList(searchMap);
        log.info("");
        log.info("");
        log.info("BoardService : selectTotalCountFaqList ... : " + result);

        return result;
    }

    public List<FaqDTO> selectFaqList(SelectCriteria selectCriteria) {
        List<FaqDTO> result = mapper.selectFaqList(selectCriteria);
        log.info("");
        log.info("");
        log.info("BoardService : selectFaqList ... : " + result);

        return result;
    }

    public List<PostImageDTO> getReviewImage(List<Integer> reviewCodes) {
        List<PostImageDTO> result = mapper.getReviewImage(reviewCodes);
        log.info("");
        log.info("");
        log.info("BoardService : getReviewImage ... : " + result);

        return result;
    }
}
