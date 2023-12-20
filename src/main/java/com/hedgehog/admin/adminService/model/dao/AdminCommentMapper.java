package com.hedgehog.admin.adminService.model.dao;

import com.hedgehog.admin.adminMember.model.dto.AdminCustomerDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminSendMailDTO;
import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminCommentMapper {
    int inquiryComment(AdminCommentDTO adminCommentDTO);

    int inquiryCommentUpdate(AdminCommentDTO adminCommentDTO);

    int updateState(AdminCommentDTO adminCommentDTO);


    AdminCustomerDTO searchMail(int userCode);

    AdminSendMailDTO searchmailForm(int i);
}
