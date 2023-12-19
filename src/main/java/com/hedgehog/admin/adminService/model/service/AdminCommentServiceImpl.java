package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminCommentMapper;
import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.exception.BoardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Slf4j
public class AdminCommentServiceImpl implements AdminCommentService {
    private final AdminCommentMapper mapper;

    public AdminCommentServiceImpl(AdminCommentMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    @Transactional
    public void inquiryComment(AdminCommentDTO adminCommentDTO) throws BoardException {
        if ("N".equals(adminCommentDTO.getAnswer_state())) {
            log.info("--------------------------------N");
            log.info("--------------------------------AdminCommentDTO" + adminCommentDTO);

            // "N"이면 댓글 삽입
            int result = mapper.inquiryComment(adminCommentDTO);
            int result2 = mapper.updateState(adminCommentDTO);

            if (result <= 0) {
                throw new BoardException("댓글 등록에 실패하셨습니다.");
            }
        } else if ("Y".equals(adminCommentDTO.getAnswer_state())) {
            // "Y"이면 댓글 업데이트
            log.info("--------------------------------Y");
            log.info("--------------------------------AdminCommentDTO" + adminCommentDTO);


            int result = mapper.inquiryCommentUpdate(adminCommentDTO);
            if (result <= 0) {
                throw new BoardException("댓글 업데이트에 실패하셨습니다.");
            }
        }
    }
    }
