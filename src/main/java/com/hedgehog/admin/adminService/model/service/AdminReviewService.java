package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewForm;
import com.hedgehog.admin.exception.BoardException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminReviewService {
    List<AdminReviewDTO> searchReview(AdminReviewForm form);

    @Transactional
    void revStateUpdate(AdminReviewDTO reviewDTO) throws BoardException;
}
