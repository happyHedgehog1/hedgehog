package com.hedgehog.admin.adminService.model.dao;

import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminCommentMapper {
    int inquiryComment(AdminCommentDTO adminCommentDTO);

    int inquiryCommentUpdate(AdminCommentDTO adminCommentDTO);

    int updateState(AdminCommentDTO adminCommentDTO);
}
