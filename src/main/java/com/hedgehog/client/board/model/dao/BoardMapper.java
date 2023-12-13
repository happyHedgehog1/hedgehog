package com.hedgehog.client.board.model.dao;

import com.hedgehog.client.board.model.dto.NoticeDTO;
import com.hedgehog.client.board.model.dto.QuestionDTO;
import com.hedgehog.client.board.model.dto.ReviewDTO;
import com.hedgehog.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int selectTotalCountQuestionList(Map<String, String> searchMap);

    int selectTotalCountReviewList(Map<String, String> searchMap);

    List<QuestionDTO> selectQuestionList(SelectCriteria selectCriteria);

    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria);

    int selectTotalCountNoticeList(Map<String, String> searchMap);

    List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);
}
