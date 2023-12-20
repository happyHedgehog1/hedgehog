package com.hedgehog.client.board.model.dao;

import com.hedgehog.client.board.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDetailMapper {
    ReviewDTO getReviewDetail(int postCode);

    QuestionDTO getQuestionDetail(int postCode);

    NoticeDTO getNoticeDetail(int postCode);

    FaqDTO getFaqDetail(int postCode);

    int getViews(int postCode);

    void setViews(int postCode, int views);

    int getReviewUserCode(String postCode);

    int getInquiryUserCode(String postCode);

    void updateReviewState(String postCode, int userCode);

    void updateInquiryState(String postCode, int userCode);

    ReviewDeleteDTO getReviewPostGrade(String postCode, int userCode);

    void updateReviewPostImage(String postCode);

    void updateInquiryPostImage(String postCode);

    ProductReviewDTO getReviewInfo(int productCode);

    void updateProductReviewCount(ProductReviewDTO newProductReviewDTO, int productCode);
}
