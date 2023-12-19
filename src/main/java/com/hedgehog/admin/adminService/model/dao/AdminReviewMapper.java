package com.hedgehog.admin.adminService.model.dao;

import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminReviewMapper {
    List<AdminReviewDTO> searchReview(AdminReviewForm form);

    int revStateUpdate(AdminReviewDTO reviewDTO);

    AdminReviewDTO reviewDetail(int Review_code);
}
