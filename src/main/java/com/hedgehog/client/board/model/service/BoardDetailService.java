package com.hedgehog.client.board.model.service;

import com.hedgehog.client.board.model.dao.BoardDetailMapper;
import com.hedgehog.client.board.model.dto.FaqDTO;
import com.hedgehog.client.board.model.dto.NoticeDTO;
import com.hedgehog.client.board.model.dto.QuestionDTO;
import com.hedgehog.client.board.model.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BoardDetailService {
    private final BoardDetailMapper mapper;

    public ReviewDTO getReviewDetail(int postCode) {
        log.info("BoardDetailService >> getReviewDetail >> postCode : " + postCode);
        ReviewDTO reviewDTO = mapper.getReviewDetail(postCode);
        log.info("BoardDetailService >> getReviewDetail >> reviewDTO : " + reviewDTO);
        return reviewDTO;
    }

    public QuestionDTO getQuestionDetail(int postCode) {
        log.info("BoardDetailService >> getQuestionDetail >> postCode : " + postCode);
        QuestionDTO questionDTO = mapper.getQuestionDetail(postCode);
        log.info("BoardDetailService >> getQuestionDetail >> questionDTO : " + questionDTO);
        return questionDTO;
    }

    public NoticeDTO getNoticeDetail(int postCode) {
        log.info("BoardDetailService >> getNoticeDetail >> postCode : " + postCode);
        NoticeDTO noticeDTO = mapper.getNoticeDetail(postCode);
        log.info("BoardDetailService >> getNoticeDetail >> noticeDTO : " + noticeDTO);
        return noticeDTO;
    }

    public FaqDTO getFaqDetail(int postCode) {
        log.info("BoardDetailService >> getFaqDetail >> postCode : " + postCode);
        FaqDTO faqDTO = mapper.getFaqDetail(postCode);
        log.info("BoardDetailService >> getFaqDetail >> questionDTO : " + faqDTO);
        return faqDTO;
    }
}
