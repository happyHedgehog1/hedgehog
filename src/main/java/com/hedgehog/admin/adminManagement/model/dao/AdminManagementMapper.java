package com.hedgehog.admin.adminManagement.model.dao;

import com.hedgehog.admin.adminManagement.model.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminManagementMapper {
    List<AdminDTO> getAdminList();
}
