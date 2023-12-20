package com.hedgehog.admin.adminMain.model.service;

import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import com.hedgehog.admin.adminMain.model.dto.AdminMainStatisticsDTO;

import java.util.List;

public interface AdminDailyVisitorsService {
    List<AdminDailyVisitorsDTO> dailyVisitors();

    AdminMainStatisticsDTO dailySales();

}
