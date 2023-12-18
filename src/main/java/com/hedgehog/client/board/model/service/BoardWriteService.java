package com.hedgehog.client.board.model.service;

import com.hedgehog.client.board.model.dao.BoardWriteMapper;
import com.hedgehog.client.board.model.dto.UploadedImageDTO;
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
        // 3. 게시물 코드 가져오기
        Integer inquiryCode = mapper.getLastInsertCode();
        if (inquiryCode == null) {
            return false;
        }
        // 사진 넣기.

        int result2 = mapper.insertPostImage(inquiryCode, uploadedImageList);

        if (result2 != uploadedImageList.size()) {
            return false;
        }

        return true;
    }
}
