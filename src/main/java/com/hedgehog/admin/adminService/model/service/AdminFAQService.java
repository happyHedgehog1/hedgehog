package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminFAQDTO;
import com.hedgehog.admin.adminService.model.dto.AdminFAQForm;
import com.hedgehog.admin.adminService.model.dto.AdminNoticeDTO;
import com.hedgehog.admin.exception.BoardException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminFAQService {

    List<AdminFAQDTO> searchFAQ(AdminFAQForm form);

    @Transactional
    void FAQStateUpdate(AdminFAQDTO faqdto) throws BoardException;

    @Transactional
    void noticeStateUpdate(AdminFAQDTO faqdto) throws BoardException;

    @Transactional
    void noticeRegister(AdminFAQDTO adminFAQDTO) throws BoardException;

    @Transactional
    void FAQRegister(AdminFAQDTO adminFAQDTO) throws BoardException;

    AdminFAQDTO FAQModifyPage(int postCode);

    void FAQModify(AdminFAQDTO adminFAQDTO);
}
