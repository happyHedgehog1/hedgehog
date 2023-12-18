package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryForm;
import com.hedgehog.admin.exception.BoardException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminInquiryService {
    List<AdminInquiryDTO> searchInquiry(AdminInquiryForm form);

    @Transactional
    void inqStateUpdate(AdminInquiryDTO inquiryDTO) throws BoardException;

    AdminInquiryDTO inquiryDetail(int inquiryCode);


}
