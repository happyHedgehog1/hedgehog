package com.hedgehog.client.board.model.dao;

import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardWriteMapper {
    int insertTblInquiry(int userCode, String option, String inputTitle, String newEditordata);

    Integer getLastInsertCode();

    int insertPostImage(Integer inquiryCode, List<UploadedImageDTO> uploadedImageList);
}
