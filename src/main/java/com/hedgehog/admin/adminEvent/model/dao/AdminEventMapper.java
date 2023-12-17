package com.hedgehog.admin.adminEvent.model.dao;

import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminEventMapper {
    List<AdminEventDTO> searchEventList(AdminEventForm form);

    List<AdminEventDTO> eventDetail(int postCode);

    List<AdminProductDTO> searchProduct(AdminEventForm form);

    int updateEventProgressionStatus(int productCode);

    int insertEventTable(AdminEventForm form);

    int insertEventProductListTable(AdminEventForm form);
}
