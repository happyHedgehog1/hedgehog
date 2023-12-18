package com.hedgehog.admin.adminService.model.dao;

import com.hedgehog.admin.adminService.model.dto.AdminFAQDTO;
import com.hedgehog.admin.adminService.model.dto.AdminFAQForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminFAQMapper {
    List<AdminFAQDTO> searchFAQ(AdminFAQForm form);

    List<AdminFAQDTO> searchNotice(AdminFAQForm form);

    int FAQStateUpdate(AdminFAQDTO faqdto);

    int noticeStateUpdate(AdminFAQDTO faqdto);

    int insertFAQ(AdminFAQDTO adminFAQDTO);

    int insertNotice(AdminFAQDTO adminFAQDTO);
}
