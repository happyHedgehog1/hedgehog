package com.hedgehog.admin.adminManagement.model.dao;

import com.hedgehog.admin.adminManagement.model.dto.AdminDTO;
import com.hedgehog.admin.adminManagement.model.dto.AdminRegistrationForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminManagementMapper {
    List<AdminDTO> getAdminList();

    int insertAdmin(AdminRegistrationForm registrationForm);

    int insertAuthorityList(Integer userCode);

    int insertWithdrawTbl(int userCode);


    int updateAdminWithdraw(int userCode);
}
