package com.hedgehog.admin.adminMain.model.service;

import com.hedgehog.admin.adminMain.model.dao.AdminDailyVisitorsServiceMapper;
import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AdminDailyVisitorsServiceImpl implements AdminDailyVisitorsService {

    private final AdminDailyVisitorsServiceMapper mapper;

    public AdminDailyVisitorsServiceImpl(AdminDailyVisitorsServiceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AdminDailyVisitorsDTO> dailyVisitors() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23,59,59);

        return mapper.dailyVisitors(startOfDay, endOfDay);
    }
}
