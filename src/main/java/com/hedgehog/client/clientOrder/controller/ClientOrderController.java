package com.hedgehog.client.clientOrder.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.service.ClientCartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientOrder")
public class ClientOrderController {

    private final ClientCartServiceImp clientCartServiceImp;

    @Autowired
    public ClientOrderController(ClientCartServiceImp clientCartServiceImp) {
        this.clientCartServiceImp = clientCartServiceImp;
    }

    @PostMapping("/cartOrder")
    public String clientOrder(@RequestParam List<Integer> cartcheckbox,
                              @RequestParam List<Integer> hdAmount,
                              Model model) {

        System.out.println("cartIds = " + cartcheckbox);  // 6,7
        System.out.println("hdAmount = " + hdAmount); //숨겨진 수량
        List<CartSelectDTO> cartList = new ArrayList<>();


        for (Integer cartCode : cartcheckbox){
            CartSelectDTO cartItem = new CartSelectDTO();
            cartList.add(cartItem);
        }

        model.addAttribute("cartList" + cartList);
        System.out.println("cartList = " + cartList);



        return "/client/content/clientOrder/cartOrder";
    }












//    @PostMapping("/cartOrder")
//    public String clientOrder(@RequestParam List<Integer> cartcheckbox,
//                              @RequestParam List<Integer> hdAmount,
//                              @AuthenticationPrincipal LoginDetails loginDetails,
//                              Model model) {
//        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
//        System.out.println(loginDetails.getLoginUserDTO().toString());
//        model.addAttribute("userCode", loginUserDTO.getUserCode());
//
//        System.out.println("cartIds = " + cartcheckbox);  // 6,7
//        System.out.println("hdAmount = " + hdAmount); //숨겨진 수량
//        List<CartSelectDTO> cartList = new ArrayList<>();
//        for (Integer cartCode : cartcheckbox) {
//            List<CartSelectDTO> product = clientCartServiceImp.cartOrderSelect(List<Integer> cartCode);
//
//            // 여기서 각각의 product를 cartList에 추가
//            cartList.addAll(product);
//        }
//
//        // 모델에 cartList 추가
//        model.addAttribute("cartList", cartList);
//
//        return "/client/content/clientOrder/cartOrder";
//    }









}
