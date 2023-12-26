package com.hedgehog.client.orderDetails.model.dao;

import com.hedgehog.client.orderDetails.model.dto.OrderDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderDetailsCollect;
import com.hedgehog.client.orderDetails.model.dto.OrderListDTO;
import com.hedgehog.common.paging.orderDetailsPaging.OrderDetailsSelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailsMapper {
    int selectTotalCountOrderInfo(int userCode, OrderDTO order, String info);

    List<OrderListDTO> selectOrderInfoList(int userCode, OrderDetailsSelectCriteria orderDetailsSelectCriteria, String info);

    int isYourOrder(int userCode, int orderCode);

    OrderDetailsCollect getOrderDetails(int orderCode);

    Integer selectOrderCode(Integer orderCode, String email);

    int selectUserCode(Integer orderCode);

    int updateReceiveOrder(String orderCode);

    String selectReceiveOrder(String orderCode);

    String selectDeliveryCode(String orderCode);

    void updateDelivery(String deliveryCode);
}
