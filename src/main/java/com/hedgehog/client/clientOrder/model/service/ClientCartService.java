package com.hedgehog.client.clientOrder.model.service;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ClientCartService {

    List<CartSelectDTO> selectCartOrder();

}
