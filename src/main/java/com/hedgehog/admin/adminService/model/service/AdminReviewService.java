package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewForm;

import java.util.List;

public interface AdminReviewService {
    List<AdminReviewDTO> searchReview(AdminReviewForm form);
}
