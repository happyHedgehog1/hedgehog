package com.hedgehog.client.clientOrder.model.service;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.dao.CartOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientCartServiceImp implements ClientCartService{

private final CartOrderMapper cartOrderMapper;

    @Autowired
    public ClientCartServiceImp(CartOrderMapper cartOrderMapper) {
        this.cartOrderMapper = cartOrderMapper;
    }

    @Override
    public List<CartSelectDTO> selectCartOrder() {
        return cartOrderMapper.cartOrderSelect();
    }

//    @Override
//    public List<Integer> carOrderSelect(List<Integer> carcheckbox, List<Integer> hdAmount) {
//        return cartOrderMapper.carOrderSelect(carcheckbox, hdAmount);
//    }


}
