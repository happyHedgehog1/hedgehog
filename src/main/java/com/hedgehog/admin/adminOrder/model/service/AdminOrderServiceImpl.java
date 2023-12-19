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

    /**
     * order.html에서
     * 배송대기, 배송중, 배송완료를 선택했으면 TBL_ORDER와 tbl_delivery의 상태 변경
     * 환불완료를 선택했으면 tbl_deliver랑 TBL_ORDER와 TBL_PAYMENT랑 tbl_refund의 state를 바꾸고,
     * 교환완료를 선택했으면 tbl_deliver랑 TBL_ORDER와 TBL_PAYMENT랑 tbl_exchange의 state를 바꾼다
     *
     *
     * @param orderDTO
     * @throws OrderStateUpdateException
     */
    @Override
    @Transactional
    public void orderStateUpdate(AdminOrderDTO orderDTO) throws OrderStateUpdateException {
        int result = 0;
        log.info("");
        if(orderDTO.getState().equals(4)) { //환불완료
            result = mapper.deliverTableUpdate(orderDTO);
            result = mapper.orderStateUpdate(orderDTO);
//            result = mapper.paymentTableUpdate(orderDTO);
//            result = mapper.refundTableUpdate(orderDTO);

        } else if (orderDTO.getState().equals(5)){ //교환완료
            result = mapper.deliverTableUpdate(orderDTO);
            result = mapper.orderStateUpdate(orderDTO);
//            result = mapper.paymentTableUpdate(orderDTO);
//            result = mapper.exchangeTableUpdate(orderDTO);

        } else {
            mapper.orderStateUpdate(orderDTO); //order테이블 state 변경
            mapper.deliveryStateUpdate(orderDTO); //delivery테이블 state 변경
            result++;
        }



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
        log.info("orderDetail -------------------------- 끗~~~~~~~~~" + orderDTO);



        return orderDTO;
    }


}
