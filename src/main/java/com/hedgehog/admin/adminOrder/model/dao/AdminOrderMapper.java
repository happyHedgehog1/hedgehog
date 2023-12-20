package com.hedgehog.admin.adminOrder.model.dao;

import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminOrderMapper {
    List<AdminOrderDTO> searchOrderList(AdminOrderForm form);

    int orderStateUpdate(AdminOrderDTO orderDTO);

    public AdminOrderDTO orderDetail(int orderCode);

    int deliverTableUpdate(AdminOrderDTO orderDTO);

    int deliveryStateUpdate(AdminOrderDTO orderDTO);

    int paymentTableUpdate(AdminOrderDTO orderDTO);

    int exchangeTableInsert(AdminOrderDTO orderDTO);

    int refundTableInsert(AdminOrderDTO orderDTO);

    int refundTableUpdate(AdminOrderDTO orderDTO);

    int exchangeTableUpdate(AdminOrderDTO orderDTO);
}
