package com.hedgehog.client.basket.controller;


import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.basket.model.dto.CartSumDTO;
import com.hedgehog.client.basket.model.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basket")
@Slf4j
public class BasketController {


    private final BasketService basketService;//서비스 주입받아서 사용
    //서비스 계층에서ㅓ 정의된 메소드 호출해서 비즈니스로직을 수행

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
        //의존성 주입
    }

    @GetMapping("/cart")
    public String selectCartList(Model model) {

            List<CartSelectDTO> cartItemList = basketService.selectCartList();
            model.addAttribute("cartItemList", cartItemList);

            List<CartSumDTO> cartSumList = basketService.selectCartSum();
            model.addAttribute("cartSumList" , cartSumList);

            int totalSum = basketService.getTotalCartSum(cartSumList);
            model.addAttribute("totalSum", totalSum);
        return "client/content/basket/cart";
    }






}
