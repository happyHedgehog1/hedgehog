package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminReviewMapper;
import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
