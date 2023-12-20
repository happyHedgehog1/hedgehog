package com.hedgehog.admin.adminMain.model.dao;

import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AdminDailyVisitorsServiceMapper {
    List<AdminDailyVisitorsDTO> dailyVisitors(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
