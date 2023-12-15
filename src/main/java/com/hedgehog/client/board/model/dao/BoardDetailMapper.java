package com.hedgehog.client.board.model.dao;

import com.hedgehog.client.board.model.dto.FaqDTO;
import com.hedgehog.client.board.model.dto.NoticeDTO;
import com.hedgehog.client.board.model.dto.QuestionDTO;
import com.hedgehog.client.board.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDetailMapper {
    ReviewDTO getReviewDetail(int postCode);

    QuestionDTO getQuestionDetail(int postCode);

    NoticeDTO getNoticeDetail(int postCode);

    FaqDTO getFaqDetail(int postCode);
}
