package com.hedgehog.client.board.model.dao;

import com.hedgehog.client.board.model.dto.QuestionDTO;
import com.hedgehog.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int selectTotalCountQuestionList(Map<String, String> searchMap);

    List<QuestionDTO> selectQuestionList(SelectCriteria selectCriteria);
}
