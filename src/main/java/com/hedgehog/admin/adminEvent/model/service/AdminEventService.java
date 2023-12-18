package com.hedgehog.admin.adminEvent.model.service;

import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;

import java.util.List;

public interface AdminEventService {
    List<AdminEventDTO> searchEventList(AdminEventForm form);


    List<AdminEventDTO> eventDetail(int postCode);

    List<AdminProductDTO> searchProduct(AdminEventForm form);

    void updateEventStatus(AdminEventForm form);

    void modifyEvent(AdminEventForm form);
}