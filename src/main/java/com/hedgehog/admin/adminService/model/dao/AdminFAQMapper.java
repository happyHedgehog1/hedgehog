package com.hedgehog.admin.adminService.model.dao;

import com.hedgehog.admin.adminService.model.dto.AdminFAQDTO;
import com.hedgehog.admin.adminService.model.dto.AdminFAQForm;
import com.hedgehog.admin.adminService.model.dto.AdminNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminFAQMapper {
    List<AdminFAQDTO> searchFAQ(AdminFAQForm form);

    List<AdminFAQDTO> searchNotice(AdminFAQForm form);

    int FAQStateUpdate(AdminFAQDTO faqdto);

    int noticeStateUpdate(AdminFAQDTO faqdto);

    int noticeRegister(AdminFAQDTO adminFAQDTO);

    int FAQRegister(AdminFAQDTO adminFAQDTO);

    AdminFAQDTO FAQModifyPage(int postCode);

    int FAQModify(AdminFAQDTO adminFAQDTO);
}
