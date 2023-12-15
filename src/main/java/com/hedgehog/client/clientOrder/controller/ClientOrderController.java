package com.hedgehog.client.clientOrder.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.service.ClientCartServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/clientOrder")
@Slf4j
public class ClientOrderController {

    private final ClientCartServiceImp clientCartService;

    @Autowired
    public ClientOrderController(ClientCartServiceImp clientCartService) {
        this.clientCartService = clientCartService;
    }

    @PostMapping("/cartOrder")
    public ModelAndView clientOrder(@RequestParam List<Integer> cartcheckbox,
                              @RequestParam List<Integer> hdAmount,
                              ModelAndView mv) {

        System.out.println("cartIds = " + cartcheckbox);  // 6,7
        System.out.println("hdAmount = " + hdAmount); //숨겨진 수량
        List<CartSelectDTO> cartList = clientCartService.selectCartOrder(cartcheckbox);
        mv.addObject("cartList" , cartList);
        log.info("cartList" + cartList);

        mv.addObject("hdAmountList", hdAmount);
        log.info("hdAmount" + hdAmount);

        int totalSum = calculateTotalSum(cartList, hdAmount);
        mv.addObject("totalSum", totalSum);

        mv.setViewName("/client/content/clientOrder/cartOrder");

        return mv;
    }

    //주문서작성에서 결제예정금액이 10만원이 넘어가면 배송비가 무료를 표현하기 위해서 구하는 전체상품 합계금액
    private int calculateTotalSum(List<CartSelectDTO> cartList, List<Integer> hdAmountList) {
        int totalSum = 0;
        for (int i = 0; i < cartList.size(); i++) {
            int price = cartList.get(i).getPrice();
            int hdAmount = hdAmountList.get(i);
            totalSum += price * hdAmount;
        }
        return totalSum;
    }


}








