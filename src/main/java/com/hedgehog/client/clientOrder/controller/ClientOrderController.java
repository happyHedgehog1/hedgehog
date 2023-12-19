package com.hedgehog.client.clientOrder.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.dto.OrderInfoDTO;
import com.hedgehog.client.clientOrder.model.service.ClientCartServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.valueOf;

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
    public ModelAndView clientOrder(@AuthenticationPrincipal LoginDetails loginDetails,
                                    @RequestParam List<Integer> cartcheckbox,
                                    @RequestParam List<Integer> hdAmount,
                                    ModelAndView mv) {

        System.out.println("cartIds = " + cartcheckbox);  // 6,7
        System.out.println("hdAmount = " + hdAmount); //숨겨진 수량

        List<CartSelectDTO> cartList = clientCartService.selectCartOrder(cartcheckbox);
        mv.addObject("cartList", cartList);
        log.info("cartList" + cartList);

        mv.addObject("hdAmountList", hdAmount);
        log.info("hdAmount" + hdAmount);
        mv.addObject("cartcheckbox", cartcheckbox);


        int totalSum = calculateTotalSum(cartList, hdAmount);
        mv.addObject("totalSum", totalSum);


        cartOrderInfo(loginDetails, mv);

        mv.setViewName("/client/content/clientOrder/cartOrder");

        return mv;
    }


//    @PostMapping("/cartOrder")
//    public String clientOrder(@AuthenticationPrincipal LoginDetails loginDetails,
//                                    @RequestParam List<Integer> cartcheckbox,
//                                    @RequestParam List<Integer> hdAmount,
//                                    Model mv) {
//
//        System.out.println("cartIds = " + cartcheckbox);  // 6,7
//        System.out.println("hdAmount = " + hdAmount); //숨겨진 수량
//
//        List<CartSelectDTO> cartList = clientCartService.selectCartOrder(cartcheckbox);
//        mv.addAttribute("cartList", cartList);
//        log.info("cartList" + cartList);
//        log.info("tttttttttttest"+ (cartList.get(0).getPrice()));
//        List<Integer> price= new ArrayList<>();
//        for (int i = 0; i < cartcheckbox.size(); i++) {
//             price.add(cartList.get(i).getPrice());
//             log.info("cartList.get(i).getPrice()"+ cartList.get(i).getPrice());
//            NumberFormat numberFormat = new NumberStyleFormatter("#,###").getNumberFormat(Locale.getDefault());
//            String formattedPrice = numberFormat.format(cartList.get(i).getPrice());
////            price.add(Integer.valueOf(formattedPrice));
//            cartList.get(i).setFormattedPrice(formattedPrice);
//        }
//
//        log.info("=========price======"+ price);
//
////        NumberFormat numberFormat = new NumberStyleFormatter("#,###").getNumberFormat(Locale.getDefault());
////        String formattedPrice = numberFormat.format(cartList.get(i).getPrice(););
////        mv.addAttribute("formattedPrice", cartList.get(i).getPrice(););
////        log.info("formattedPrice=================="+ formattedPrice);
//
////        Long productPriceObject = Long.valueOf(productPrice);
////        log.info("상품가격 타입: " + productPriceObject.getClass().getName());
//
//
//        mv.addAttribute("hdAmountList", hdAmount);
//        log.info("hdAmount" + hdAmount);
//        mv.addAttribute("cartcheckbox", cartcheckbox);
//
//
//        int totalSum = calculateTotalSum(cartList, hdAmount);
//        mv.addAttribute("totalSum", totalSum);
//
//
//        cartOrderInfo(loginDetails, mv);
//
//        return  "/client/content/clientOrder/cartOrder";
//
//
//    }
//
//
//

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

    public void cartOrderInfo(LoginDetails loginDetails,
                              ModelAndView mv){
        //적립금, 주문지명, 휴대전화, 이메일, 주소는 직접
        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
        int point = clientCartService.getOrderPoint(loginUserDTO.getUserCode());
        mv.addObject("name", loginUserDTO.getName());
        mv.addObject("point", point);

        //주문정보에서 사용자 정보
        OrderInfoDTO orderInfo = clientCartService.getOrderInfo(loginUserDTO.getUserCode());
        mv.addObject("phone", orderInfo.getPhone());
        mv.addObject("email" , orderInfo.getEmail());

    }



    @PostMapping("/processPayment")
    public ModelAndView processPayment(@AuthenticationPrincipal LoginDetails loginDetails,
                                       ModelAndView mv){


        return mv;
    }

}

