package com.hedgehog.admin.adminEvent.model.service;

import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;

import java.util.List;

public interface AdminEventService {
    public List<AdminEventDTO> searchEventList(AdminEventForm form);


//    List<AdminEventDTO> eventDetail(AdminEventDTO eventDTO);
}