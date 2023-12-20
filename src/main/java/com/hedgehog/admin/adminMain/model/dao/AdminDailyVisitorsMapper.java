package com.hedgehog.admin.adminMain.model.dao;

import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AdminDailyVisitorsMapper {
    List<AdminDailyVisitorsDTO> dailyVisitors(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<String> dailySales(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<String> dailySaleVolume(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
