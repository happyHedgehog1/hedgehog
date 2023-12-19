package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.exception.BoardException;
import org.springframework.transaction.annotation.Transactional;

public interface AdminCommentService {
    @Transactional
    void inquiryComment(AdminCommentDTO adminCommentDTO) throws BoardException;
}

