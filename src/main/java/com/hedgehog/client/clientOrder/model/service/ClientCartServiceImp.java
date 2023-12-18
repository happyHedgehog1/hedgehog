package com.hedgehog.client.clientOrder.model.service;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.dao.CartOrderMapper;
import com.hedgehog.client.clientOrder.model.dto.OrderInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ClientCartServiceImp implements ClientCartService {

    private final CartOrderMapper cartOrderMapper;

    @Autowired
    public ClientCartServiceImp(CartOrderMapper cartOrderMapper) {
        this.cartOrderMapper = cartOrderMapper;
    }

    @Override
    public List<CartSelectDTO> selectCartOrder(List<Integer> cartcheckbox) {
        return cartOrderMapper.cartOrderSelect(cartcheckbox);
    }

    @Override
    public int getOrderPoint(int userCode) {
        return cartOrderMapper.getOrderPoint(userCode);
    }


    @Transactional
    @Override
    public void updateUserPoint(int userCode, int usedPoints) {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("userCode", userCode);
//        parameters.put("usedPoints", usedPoints);
        cartOrderMapper.updateUserPoint(userCode, usedPoints);
    }


    @Override
    public OrderInfoDTO getOrderInfo(int userCode) {
        return cartOrderMapper.getOrderInfo(userCode);
    }



}
