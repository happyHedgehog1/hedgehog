package com.hedgehog.admin.adminMain.model.dao;

import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import com.hedgehog.admin.adminMain.model.dto.AdminMainStatisticsDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AdminDailyVisitorsMapper {
    List<AdminDailyVisitorsDTO> dailyVisitors(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<String> dailySales(LocalDateTime startOfDay, LocalDateTime endOfDay);


    List<String> dailyReviews(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<String> dailyUser(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<String> dailyInquiry(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<String> dailyOrder(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<String> dailyDelivery(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<AdminInquiryDTO> searchInquiry();

    List<AdminReviewDTO> searchReview();

    List<String> dailySaleVolume(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
