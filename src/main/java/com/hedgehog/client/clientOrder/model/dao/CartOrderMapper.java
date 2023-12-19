package com.hedgehog.client.clientOrder.model.dao;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.dto.OrderInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartOrderMapper {


    List<CartSelectDTO> cartOrderSelect(List<Integer> cartcheckbox);

    int getOrderPoint(int userCode);

    OrderInfoDTO getOrderInfo(int userCode);

    int updateUserPoint(int userCode, int usedPoints);
}
