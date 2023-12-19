package com.hedgehog.client.board.model.dao;

import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderDetailsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardWriteMapper {
    int insertTblInquiry(int userCode, String option, String inputTitle, String newEditordata);

    Integer getLastInsertCodeInquiry();

    int insertPostImageInquiry(Integer inquiryCode, List<UploadedImageDTO> uploadedImageList);

    String findMyIdByOrderDetailsCode(int orderDetailsCode);

    OrderDetailsDTO selectOrderDetail(int orderDetailsCode);

    int insertTblReview(int userCode, String editordata, OrderDetailsDTO orderDetailsDTO, String stars);

    Integer getLastInsertCodeReview();

    int insertPostImageReview(Integer reviewCode, List<UploadedImageDTO> uploadedImageList);

    int updateReviewPoint(int orderDetailsCode);

    Integer selectMemberPoint(int userCode);

    void updateMemberPoint(int userCode, int point);
}
