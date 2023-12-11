package com.hedgehog.admin.adminEvent.model.service;

import com.hedgehog.admin.adminEvent.model.dao.AdminEventMapper;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminEventServiceImpl implements AdminEventService{
    private final AdminEventMapper mapper;

    public AdminEventServiceImpl(AdminEventMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<AdminEventDTO> searchEventList(AdminEventForm form) {
        List<AdminEventDTO> eventList = mapper.searchEventList(form);
        return eventList;
    }
}
