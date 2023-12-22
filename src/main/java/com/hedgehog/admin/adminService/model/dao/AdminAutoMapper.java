package com.hedgehog.admin.adminService.model.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminAutoMapper {
    AdminAutoMailDTO previewMail(int mailCode);

    int modifyMail(AdminAutoMailDTO mailDTO);

    
    int insertMailHistory(AdminAutoMailDTO adminAutoMailDTO);

    int imgInsert(List<UploadedImageDTO> uploadedImageList, int mailCode);

    String[] searchEmailList();

    List<AdminAutoMailDTO> searchEmailHistory(AdminAutoMailDTO mailDTO);
}
