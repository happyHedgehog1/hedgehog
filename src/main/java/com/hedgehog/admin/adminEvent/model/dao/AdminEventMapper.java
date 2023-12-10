package com.hedgehog.admin.adminEvent.model.dao;

import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminEventMapper {
    List<AdminEventDTO> searchEventList(AdminEventForm form);
}
