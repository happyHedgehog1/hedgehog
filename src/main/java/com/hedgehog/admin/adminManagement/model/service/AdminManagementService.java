package com.hedgehog.admin.adminManagement.model.service;

import com.hedgehog.admin.adminManagement.controller.AdminManagementController;
import com.hedgehog.admin.adminManagement.model.dao.AdminManagementMapper;
import com.hedgehog.admin.adminManagement.model.dto.AdminDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminManagementService {
    private final AdminManagementMapper mapper;

    public AdminManagementService(AdminManagementMapper mapper) {
        this.mapper = mapper;
    }

    public List<AdminDTO> getAdminList() {
        List<AdminDTO> adminList = mapper.getAdminList();
        return adminList;
    }
}
