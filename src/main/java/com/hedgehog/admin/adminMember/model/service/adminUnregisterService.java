package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dto.adminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.adminUnregisterForm;

import java.util.List;


public interface adminUnregisterService {
    List<adminUnregisterDTO> selectUnregister(adminUnregisterForm form);

}
