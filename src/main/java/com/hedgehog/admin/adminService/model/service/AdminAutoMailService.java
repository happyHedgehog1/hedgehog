package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailForm;
import com.hedgehog.admin.exception.AdminProductAddException;
import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AdminAutoMailService {
    AdminAutoMailDTO previewMail(int mailCode);

    public void modifyMail(AdminAutoMailDTO mailDTO) throws AdminProductAddException;

    boolean sendMail(List<UploadedImageDTO> uploadedImageList, String title, String summernote, String chooseMember) throws MessagingException, UnsupportedEncodingException;

    List<AdminAutoMailDTO> searchEmailHistory(AdminAutoMailForm form);

    AdminAutoMailDTO emailDetail(int mailCode);
}
