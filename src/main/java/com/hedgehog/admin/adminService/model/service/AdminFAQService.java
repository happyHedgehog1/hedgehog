package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminFAQDTO;
import com.hedgehog.admin.adminService.model.dto.AdminFAQForm;
import com.hedgehog.admin.exception.BoardException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminFAQService {

    List<AdminFAQDTO> searchFAQ(AdminFAQForm form);

    void FAQStateUpdate(AdminFAQDTO faqdto) throws BoardException;

    void insertFAQ(AdminFAQDTO adminFAQDTO) throws BoardException;

    void insertNotice(AdminFAQDTO adminFAQDTO) throws BoardException;
}
