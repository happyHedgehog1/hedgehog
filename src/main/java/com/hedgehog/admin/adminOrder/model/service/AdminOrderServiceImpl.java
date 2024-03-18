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
        log.info("");

        int result = 0;
        int result2 =0;
        if("4".equals(orderDTO.getState())) { //환불완료
            //tbl_order state 환불완료로 변경
            mapper.orderStateUpdate(orderDTO);
            result++;
            //tbl_payment state를 환불완료로 변경
            mapper.paymentTableUpdate(orderDTO);
            result++;
            //tbl_refund에 state 환불완료로 변경
            mapper.refundTableUpdate(orderDTO);
            result++;
            //tbl_deliver에 state 환불완료로 변경
            mapper.deliverTableUpdate(orderDTO);
            result++;
        } else if ("5".equals(orderDTO.getState())){ //교환완료
            //tbl_order state 교환완료로 변경
            mapper.orderStateUpdate(orderDTO);
            result++;
            //tbl_payment state를 교환완료로 변경
            mapper.paymentTableUpdate(orderDTO);
            result++;
            //tbl_exchange에 state 교환완료로 변경
            mapper.exchangeTableUpdate(orderDTO);
            result++;
            //tbl_deliver에 state 교환완료로 변경
            mapper.deliverTableUpdate(orderDTO);
            result++;
        } else {
            mapper.orderStateUpdate(orderDTO); //order테이블 state 변경
            result2++;
            mapper.deliveryStateUpdate(orderDTO); //delivery테이블 state 변경
            result2++;
            //tbl_payment state 변경
            mapper.paymentTableUpdate(orderDTO);
            result2++;

        }
        if(!(result > 3 || result2 > 1)) {
            throw new OrderStateUpdateException("상태 변경에 실패하셨습니다.");
        }
    }

    /**
     * 주문내역 상세조회 메소드
     * @param orderCode
     * @return
     */
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

    /**
     * 교환신청 메소드
     * @param orderDTO orderCode랑 교환사유
     */
    @Override
    @Transactional
    public void exchange(AdminOrderDTO orderDTO) throws OrderStateUpdateException {
        //tbl_order state 교환중으로 변경
        int result1 = mapper.orderStateUpdate(orderDTO);
        //tbl_deliver에 state 교환중으로 변경
        int result2 = mapper.deliverTableUpdate(orderDTO);
        //tbl_payment state를 교환중으로 변경
        int result3 = mapper.paymentTableUpdate(orderDTO);
        //tbl_exchange에 insert
        int result4 = mapper.exchangeTableInsert(orderDTO);
        if(!(result1 > 0 ||result2 > 0 || result3 > 0 ||result4 > 0)) {
            throw new OrderStateUpdateException("상태 변경에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void refund(AdminOrderDTO orderDTO) throws OrderStateUpdateException {
        log.info("");
        log.info("");
        log.info("refund -------------------------- 시작~~~~~~~~~");
        //tbl_order state 환불중으로 변경
        int result1 = mapper.orderStateUpdate(orderDTO);
        //tbl_deliver에 state 환불중으로 변경
        int result2 = mapper.deliverTableUpdate(orderDTO);
        //tbl_payment state를 환불중으로 변경
        int result3 = mapper.paymentTableUpdate(orderDTO);
        //tbl_refund에 insert
        int result4 = mapper.refundTableInsert(orderDTO);
        if(!(result1 > 0 ||result2 > 0 || result3 > 0 ||result4 > 0)) {
            throw new OrderStateUpdateException("상태 변경에 실패하셨습니다.");
        }
    }


}
