package com.hedgehog.admin.adminManagement.model.dao;

import com.hedgehog.admin.adminManagement.model.dto.AdminDTO;
import com.hedgehog.admin.adminManagement.model.dto.AdminRegistrationForm;
import com.hedgehog.admin.adminManagement.model.dto.ChangePwdForm;
import com.hedgehog.common.paging.adminManagementPaging.AdminManagementSelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminManagementMapper {
    int selectTotalCountAdminInfo();

    List<AdminDTO> getAdminList(AdminManagementSelectCriteria adminManagementSelectCriteria);

    int insertAdmin(AdminRegistrationForm registrationForm);

    int insertAuthorityList(Integer userCode);

    int insertWithdrawTbl(int userCode);


    int updateAdminWithdraw(int userCode);

    int updateAdminPwd(ChangePwdForm newPwdForm);

}
