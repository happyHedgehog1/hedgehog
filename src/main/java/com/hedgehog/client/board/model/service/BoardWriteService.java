package com.hedgehog.client.board.model.service;

import com.hedgehog.client.board.model.dao.BoardWriteMapper;
import com.hedgehog.client.board.model.dto.ProductReviewDTO;
import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BoardWriteService {
    private final BoardWriteMapper mapper;

    @Transactional
    public boolean questionRegist(int userCode, String option, String inputTitle, String newEditordata, List<UploadedImageDTO> uploadedImageList) {
        // 1. 현재 로그인한 계정의 정보 -> Controller 에서 userCode를 가져왔다.
        // 2. insert tbl_inquiry
        // 3. 게시글의 번호 가져오기
        // 4. insert tbl_post_img .. 이 네가지가 트랜잭션 하나이므로. Service를 부른다.
        log.info("BoardWriteService 에 잘 왔나... questionRegist : ");
        log.info("userCode >> " + userCode); // 현재 로그인한 유저 정보
        log.info("option >> " + option); // 게시글 분류
        log.info("inputTitle >> " + inputTitle); // 제목
        log.info("newEditordata >> " + newEditordata); // 새롭게 정의한 summernote 내부 데이터
        log.info("uploadedImageList >> " + uploadedImageList); // 이미지 모음. tbl_post_image에 사용

        // 2. insert tbl_inquiry
        int result = mapper.insertTblInquiry(userCode, option, inputTitle, newEditordata);
        if (result != 1) {
            return false;
        }
        log.info("tbl_inquiry에 값이 들어갔냐... : " + (result == 1 ? true : false));
        // 3. 게시물 코드 가져오기
        Integer inquiryCode = mapper.getLastInsertCodeInquiry();
        if (inquiryCode == null) {
            return false;
        }
        log.info("getLastInsertCodeInquiry로 값을 받았냐... : " + inquiryCode);
        // 사진 넣기.

        int result2 = 0;
        if (uploadedImageList.size() != 0) {
            result2 = mapper.insertPostImageInquiry(inquiryCode, uploadedImageList);
        }
        if (result2 != uploadedImageList.size()) {
            return false;
        }
        log.info("이미지가 올라가긴 했냐 : " + result2);


        return true;
    }

    public String findMyIdByOrderDetailsCode(int orderDetailsCode) {
        String result = mapper.findMyIdByOrderDetailsCode(orderDetailsCode);

        log.info("아이디 찾아봤냐... : " + result);
        return result;
    }

    public OrderDetailsDTO selectOrderDetail(int orderDetailsCode) {
        OrderDetailsDTO result = mapper.selectOrderDetail(orderDetailsCode);
        log.info("주문 상세 정보... : " + result);
        return result;
    }

    @Transactional
    public boolean reviewRegist(int userCode, String editordata, OrderDetailsDTO orderDetailsDTO, String stars, List<UploadedImageDTO> uploadedImageList) {

        // 1. 현재 로그인한 계정의 정보
        // 2. insert tbl_review
        // 3. 게시글의 번호 가져오기
        // 4. insert tbl_post_img ..
        // 5. update tbl_order_details ..
        // 6. select and update tbl_member -> point 목적
        // 7. update tbl_product ...
        log.info("BoardWriteService 에 잘 왔나... reviewRegist : ");
        log.info("userCode >> " + userCode); // 현재 로그인한 유저 정보
        log.info("orderDetailsDTO >> " + orderDetailsDTO); // 현재 주문 상세 정보 제품 하나
        log.info("stars >> " + stars); // 별점
        log.info("uploadedImageList >> " + uploadedImageList); // 이미지 모음. tbl_post_image에 사용

        // 2. insert tbl_review
        int result = mapper.insertTblReview(userCode, editordata, orderDetailsDTO, stars);
        if (result != 1) {
            return false;
        }
        log.info("tbl_review에 값이 들어갔냐... : " + (result == 1 ? true : false));
        // 3. 게시물 코드 가져오기
        Integer reviewCode = mapper.getLastInsertCodeReview();
        if (reviewCode == null) {
            return false;
        }
        log.info("getLastInsertCodeReview로 값을 받았냐... : " + reviewCode);
        // 4. insert tbl_post_img ...
        int result2 = mapper.insertPostImageReview(reviewCode, uploadedImageList);
        if (result2 != uploadedImageList.size()) {
            return false;
        }
        log.info("이미지가 올라가긴 했냐 : " + result2);

        // 5. update tbl_order_details ..
        int result3 = mapper.updateReviewPoint(orderDetailsDTO.getOrderDetailsCode());
        if (result3 != 1) {
            return false;
        }
        log.info("리뷰 포인트가 올라가긴 했냐 : " + result3);

        // 6. select and update tbl_member -> point 목적
        Integer point = mapper.selectMemberPoint(userCode);
        if (point != null) {
            return false;
        }
        point += 1000;
        mapper.updateMemberPoint(userCode, point);


        // 6. update tbl_product ...
        ProductReviewDTO productReviewDTO = mapper.getReviewInfo(orderDetailsDTO.getProductCode());
        int reviews = productReviewDTO.getReviews();
        double grade = productReviewDTO.getGrade();
        ProductReviewDTO newProductReviewDTO = new ProductReviewDTO(reviews + 1, (grade * reviews + orderDetailsDTO.getReviewPoint()) / (reviews + 1));
        mapper.updateProductReviewCount(newProductReviewDTO, orderDetailsDTO.getProductCode());

        return true;
    }
}
