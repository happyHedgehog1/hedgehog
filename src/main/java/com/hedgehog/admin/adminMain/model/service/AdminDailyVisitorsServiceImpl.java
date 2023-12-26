package com.hedgehog.admin.adminMain.model.service;

import com.hedgehog.admin.adminMain.model.dao.AdminDailyVisitorsMapper;
import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import com.hedgehog.admin.adminMain.model.dto.AdminMainStatisticsDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminDailyVisitorsServiceImpl implements AdminDailyVisitorsService {

    private final AdminDailyVisitorsMapper mapper;

    public AdminDailyVisitorsServiceImpl(AdminDailyVisitorsMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AdminDailyVisitorsDTO> dailyVisitors() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23,59,59);
log.info("==============");
        return mapper.dailyVisitors(startOfDay, endOfDay);
    }

    @Override
    public AdminMainStatisticsDTO dailySales() {

        AdminMainStatisticsDTO adminMainStatisticsDTO = new AdminMainStatisticsDTO();

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23,59,59);
//        매출 조회
        List<String> dailySales = mapper.dailySales(startOfDay,endOfDay);
        int result =0;
        for (int i = 0; i < dailySales.size() ; i++) {
            result += Integer.parseInt(dailySales.get((i)));
        }
        log.info("================dailySales" + dailySales);
        adminMainStatisticsDTO.setSales(result);


//        adminMainStatisticsDTO.setSales(result);
//        List<String> dailyReview = mapper.dailtReviews(startOfDay, endOfDay);
//        int result1 = dailyReview.size();
//        log.info("===================dailyReview.size()" + result1);
//        return null;
//      판매량 조회
        List<String> dailySaleVolume = mapper.dailySaleVolume(startOfDay, endOfDay);
        int result1 = dailySaleVolume.size();
        adminMainStatisticsDTO.setSaleVolume(result1);

//        log.info("===================dailySaleVolume.size()" + result1);
        log.info("===================dailySaleVolume" + result1);

        //리뷰 조회
        List<String> dailyReview = mapper.dailyReviews(startOfDay, endOfDay);
        int result2 = dailyReview.size();
        adminMainStatisticsDTO.setReviews(result2);
        log.info("===================dailyReview.size()" + result2);

        //신규가입 조회
        List<String> dailyUser = mapper.dailyUser(startOfDay, endOfDay);
        int result3 = dailyUser.size();
        adminMainStatisticsDTO.setUser(result3);
        log.info("===================dailyUser.size()" + result3);

        //문의수 조회
        List<String> dailyInquiry = mapper.dailyInquiry(startOfDay, endOfDay);
        int result4 = dailyInquiry.size();
        adminMainStatisticsDTO.setInquiry(result4);
        log.info("===================dailyInquiry.size()" + result4);

        //배송중 조회
        List<String> dailyOrder = mapper.dailyOrder(startOfDay, endOfDay);
        int result5 = dailyOrder.size();
        adminMainStatisticsDTO.setOrder(result5);
        log.info("===================dailyOrder.size()" + result5);

        //배송완료 조회
        List<String> dailyDelivery = mapper.dailyDelivery(startOfDay, endOfDay);
        int result6 = dailyDelivery.size();
        adminMainStatisticsDTO.setDelivery(result6);
        log.info("===================dailyDelivery.size()" + result6);

        return adminMainStatisticsDTO;
    }

    @Override
    public List<AdminInquiryDTO> inquiry() {
        List<AdminInquiryDTO> adminInquiryDTO = mapper.searchInquiry();
        log.info("===================adminInquiryDTO" + adminInquiryDTO);
        return adminInquiryDTO;
    }

    @Override
    public List<AdminReviewDTO> review() {
        List<AdminReviewDTO> reviewDTO = mapper.searchReview();
        log.info("===================reviewDTO" + reviewDTO);
        return reviewDTO;
    }

}
