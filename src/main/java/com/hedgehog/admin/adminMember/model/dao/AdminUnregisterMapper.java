package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.AdminCustomerDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminSendMailDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminUnregisterMapper {
    List<AdminUnregisterDTO> selectUnregister(AdminUnregisterForm form);

    int causeUpdate(AdminUnregisterDTO adminUnregisterDTO);

    int withdrawalCancel(AdminUnregisterDTO adminUnregisterDTO);

    AdminUnregisterDTO unregisterDetail(int userCode);


    AdminSendMailDTO searchMailForm(int i);

    AdminCustomerDTO searchMail(int userCode);

    void userTableStateUpdate(AdminUnregisterDTO adminUnregisterDTO);
}
