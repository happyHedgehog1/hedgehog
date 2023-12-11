package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterForm;
import com.hedgehog.admin.exception.UnregistException;

import java.util.List;


public interface AdminUnregisterService {
    List<AdminUnregisterDTO> selectUnregister(AdminUnregisterForm form);

    public void causeUpdate(AdminUnregisterDTO adminUnregisterDTO) throws UnregistException;
}
