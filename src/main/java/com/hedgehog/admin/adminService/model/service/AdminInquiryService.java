package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryForm;

import java.util.List;

public interface AdminInquiryService {
    List<AdminInquiryDTO> searchInquiry(AdminInquiryForm form);
}
