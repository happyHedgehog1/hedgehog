package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.exception.BoardException;
import jakarta.mail.MessagingException;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

public interface AdminCommentService {
    @Transactional
    void inquiryComment(AdminCommentDTO adminCommentDTO) throws BoardException, MessagingException, UnsupportedEncodingException;
}

