package com.hedgehog.admin.adminOrder.model.service;

import com.hedgehog.admin.adminOrder.model.dao.AdminOrderMapper;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderForm;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.exception.OrderStateUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminOrderServiceImpl implements AdminOrderService{
    private final AdminOrderMapper mapper;

    public AdminOrderServiceImpl(AdminOrderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AdminOrderDTO> searchOrderList(AdminOrderForm form) {
        List<AdminOrderDTO> orderList = mapper.searchOrderList(form);
        return orderList;
    }

    @Override
    @Transactional
    public void orderStateUpdate(AdminOrderDTO orderDTO) throws OrderStateUpdateException {

        log.info("");

        int result = mapper.orderStateUpdate(orderDTO);
        log.info(" orderState result =================================== ", result);

        if(!(result > 0)) {
            throw new OrderStateUpdateException("상태 변경에 실패하셨습니다.");
        }
    }

    @Override
    public AdminOrderDTO orderDetail(int orderCode) {
        log.info("");
        log.info("");
        log.info("orderDetail -------------------------- 시작~~~~~~~~~");

        AdminOrderDTO orderDTO = null;


        orderDTO = mapper.orderDetail(orderCode);
        log.info("selectProductDetail -------------------------- 끗~~~~~~~~~" + orderDTO);



        return orderDTO;
    }


}
