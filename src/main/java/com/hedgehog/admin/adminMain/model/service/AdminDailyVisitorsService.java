package com.hedgehog.admin.adminMain.model.service;

import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import com.hedgehog.admin.adminMain.model.dto.AdminMainStatisticsDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;

import java.util.List;

public interface AdminDailyVisitorsService {
    List<AdminDailyVisitorsDTO> dailyVisitors();

    AdminMainStatisticsDTO dailySales();

    List<AdminInquiryDTO> inquiry();

    List<AdminReviewDTO> review();
}
