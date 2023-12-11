package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminUnregisterMapper {
    List<AdminUnregisterDTO> selectUnregister(AdminUnregisterForm form);

    int causeUpdate(AdminUnregisterDTO adminUnregisterDTO);
}
