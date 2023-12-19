package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryForm;
import com.hedgehog.admin.exception.BoardException;
import jakarta.mail.MessagingException;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AdminInquiryService {
    //상품문의 메일발송
//    @Transactional
//    void inqMail(AdminInquiryDTO adminInquiryDTO) throws BoardException, MessagingException, UnsupportedEncodingException;

    List<AdminInquiryDTO> searchInquiry(AdminInquiryForm form);

    @Transactional
    void inqStateUpdate(AdminInquiryDTO inquiryDTO) throws BoardException;

    AdminInquiryDTO inquiryDetail(int inquiryCode);



}
