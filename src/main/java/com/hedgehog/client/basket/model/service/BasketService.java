package com.hedgehog.client.basket.model.service;

import com.hedgehog.client.basket.model.dto.BasketDTO;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import org.apache.catalina.User;

import java.util.List;

public interface BasketService {
    List<CartSelectDTO> selectCartList();

}
