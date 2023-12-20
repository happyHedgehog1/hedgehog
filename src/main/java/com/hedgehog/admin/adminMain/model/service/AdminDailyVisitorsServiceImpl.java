package com.hedgehog.admin.adminMain.model.service;

import com.hedgehog.admin.adminMain.model.dao.AdminDailyVisitorsMapper;
import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import com.hedgehog.admin.adminMain.model.dto.AdminMainStatisticsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        List<String> dailySales = mapper.dailySales(startOfDay,endOfDay);
        int result =0;
        for (int i = 0; i < dailySales.size() ; i++) {
            result += Integer.parseInt(dailySales.get((i)));
        }
        log.info("================dailySales" + dailySales);


//        adminMainStatisticsDTO.setSales(result);
//        List<String> dailyReview = mapper.dailtReviews(startOfDay, endOfDay);
//        int result1 = dailyReview.size();
//        log.info("===================dailyReview.size()" + result1);
//        return null;

        adminMainStatisticsDTO.setSales(result);
        List<String> dailySaleVolume = mapper.dailySaleVolume(startOfDay, endOfDay);
        int result1 = dailySaleVolume.size();
        adminMainStatisticsDTO.setSaleVolume(result1);
        log.info("===================dailyReview.size()" + result1);



        return adminMainStatisticsDTO;
    }

}
