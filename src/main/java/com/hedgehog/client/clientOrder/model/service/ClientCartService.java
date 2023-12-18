package com.hedgehog.client.clientOrder.model.service;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.dto.OrderInfoDTO;

import java.util.List;

public interface ClientCartService {

    List<CartSelectDTO> selectCartOrder(List<Integer> cartcheckbox);
    int getOrderPoint(int userCode);

    void updateUserPoint(int userCode, int usedPoints);

    OrderInfoDTO getOrderInfo(int userCode);


}
