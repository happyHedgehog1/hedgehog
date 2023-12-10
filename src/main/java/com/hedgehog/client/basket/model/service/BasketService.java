package com.hedgehog.client.basket.model.service;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.basket.model.dto.CartSumDTO;

import java.util.List;

public interface BasketService {
    List<CartSelectDTO> selectCartList();

    List<CartSumDTO> selectCartSum();

    int getTotalCartSum(List<CartSumDTO> cartSumDTOList);




}
