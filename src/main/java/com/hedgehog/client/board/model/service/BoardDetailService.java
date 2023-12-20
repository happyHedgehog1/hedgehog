package com.hedgehog.client.board.model.service;

import com.hedgehog.client.board.model.dao.BoardDetailMapper;
import com.hedgehog.client.board.model.dto.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void addViews(int postCode) {
        log.info("공지사항. faq. >> postCode :" + postCode);
        int views = mapper.getViews(postCode);
        log.info("과거 조회수... : " + views);
        mapper.setViews(postCode, views + 1);
        log.info("미래 조회수... :" + views + 1);
    }

    public int getReviewUserCode(String postCode) {
        log.info("리뷰게시글의 유저코드.. 가져와보자");
        int reviewUserCode = mapper.getReviewUserCode(postCode);
        log.info("리뷰게시글의 유저코드 가져옴... : " + reviewUserCode);
        return reviewUserCode;
    }

    public int getInquiryUserCode(String postCode) {
        log.info("문의게시글의 유저코드.. 가져와보자");
        int inquiryUserCode = mapper.getInquiryUserCode(postCode);
        log.info("문의게시글의 유저코드 가져옴... : " + inquiryUserCode);
        return inquiryUserCode;
    }

    @Transactional
    public void deleteReview(String postCode, int userCode) {
        /*3. tbl_review의 state를 변경한다.*/
        mapper.updateReviewState(postCode, userCode);
        /*3-2. tbl_review에서 grade와 product_code를 가져온다.*/
        ReviewDeleteDTO reviewDeleteDTO = mapper.getReviewPostGrade(postCode, userCode);
        int reviewGrade = reviewDeleteDTO.getGrade();
        int productCode = reviewDeleteDTO.getProductCode();
        /*4. tbl_post_img 의 state를 변경한다.*/
        mapper.updateReviewPostImage(postCode);
        /*5. tbl_product의 reviews와 grade를 가져온다.*/
        ProductReviewDTO productReviewDTO = mapper.getReviewInfo(productCode);
        /*6. reviews와 grade를 적절히 변화시킨다. Div/0 주의!*/
        int reviews = productReviewDTO.getReviews();
        double grade = productReviewDTO.getGrade();
        ProductReviewDTO newProductReviewDTO = new ProductReviewDTO(reviews - 1, ((reviews - 1) != 0) ? (grade * reviews - reviewGrade) / (reviews - 1) : 0);
        mapper.updateProductReviewCount(newProductReviewDTO, productCode);
    }

    @Transactional
    public void deleteInquiry(String postCode, int userCode) {
        /*3. tbl_inquiry의 state를 변경한다.*/
        mapper.updateInquiryState(postCode, userCode);
        /*4. tbl_post_img 의 state를 변경한다.*/
        mapper.updateInquiryPostImage(postCode);
    }
}
