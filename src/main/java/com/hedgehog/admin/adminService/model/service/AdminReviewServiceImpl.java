package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminReviewMapper;
import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewForm;
import com.hedgehog.admin.exception.BoardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
public class AdminReviewServiceImpl implements AdminReviewService{

    private final AdminReviewMapper mapper;

    public AdminReviewServiceImpl(AdminReviewMapper mapper) {this.mapper = mapper;}
@Override
    public  List<AdminReviewDTO> searchReview(AdminReviewForm form) {
        List<AdminReviewDTO> reviewList = mapper.searchReview(form);
        return reviewList;
    }
@Override
@Transactional
    public void revStateUpdate(AdminReviewDTO reviewDTO) throws BoardException {
    log.info("");

    int result = mapper.revStateUpdate(reviewDTO);
    log.info("===========revStateUpdate Result {}", result);

    if (!(result > 0)){
        throw new BoardException("상태 변경에 실패하셨습니다.");
    }
    }
}
