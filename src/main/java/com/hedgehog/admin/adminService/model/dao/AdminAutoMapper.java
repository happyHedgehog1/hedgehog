package com.hedgehog.admin.adminService.model.dao;

import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminAutoMapper {
    AdminAutoMailDTO previewMail(int mailCode);

    int modifyMail(AdminAutoMailDTO mailDTO);
}
