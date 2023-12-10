package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.adminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.adminUnregisterForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface adminUnregisterMapper {
    List<adminUnregisterDTO> selectUnregister(adminUnregisterForm form);
}
