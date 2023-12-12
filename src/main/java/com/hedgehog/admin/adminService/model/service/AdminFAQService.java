package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminFAQDTO;
import com.hedgehog.admin.adminService.model.dto.AdminFAQForm;

import java.util.List;

public interface AdminFAQService {

    List<AdminFAQDTO> searchFAQ(AdminFAQForm form);
}
