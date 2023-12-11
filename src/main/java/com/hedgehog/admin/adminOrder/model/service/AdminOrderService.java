package com.hedgehog.admin.adminOrder.model.service;


import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderForm;
import com.hedgehog.admin.exception.OrderStateUpdateException;

import java.util.List;

public interface AdminOrderService {
    List<AdminOrderDTO> searchOrderList(AdminOrderForm form);

    public void orderStateUpdate(AdminOrderDTO orderDTO) throws OrderStateUpdateException;
}
