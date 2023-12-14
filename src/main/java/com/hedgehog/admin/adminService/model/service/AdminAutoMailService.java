package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.admin.exception.AdminProductAddException;

public interface AdminAutoMailService {
    AdminAutoMailDTO previewMail(int mailCode);

    public void modifyMail(AdminAutoMailDTO mailDTO) throws AdminProductAddException;
}
