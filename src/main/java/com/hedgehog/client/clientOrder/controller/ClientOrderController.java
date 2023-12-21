package com.hedgehog.client.clientOrder.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.clientOrder.model.dto.OrderInfoDTO;
import com.hedgehog.client.clientOrder.model.service.ClientCartServiceImp;
import com.hedgehog.client.kakaopay.model.dto.ApproveResponse;
import com.hedgehog.client.kakaopay.model.dto.ReadyResponse;
import com.hedgehog.client.kakaopay.model.service.KakaoPayService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.lang.Integer.valueOf;

@Controller
@RequestMapping("/clientOrder")
@Slf4j
public class ClientOrderController {

    private final ClientCartServiceImp clientCartService;
    private final KakaoPayService kakaoPayService;

    @Autowired
    public ClientOrderController(ClientCartServiceImp clientCartService, KakaoPayService kakaoPayService) {
        this.clientCartService = clientCartService;
        this.kakaoPayService = kakaoPayService;
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





    //여기부터 주문성공 및 실패 페이지

    @GetMapping("/orderCompleted") //주문완료 페이지
    public String orderCompleted(
                                 @RequestParam("pg_token") String pgToken,
                                 @ModelAttribute("tid") String tid,
//                                 @RequestParam(value = "AllOriginalTotalOrder", required = false) int AllOriginalTotalOrder,
//                                 HttpSession httpSession,
                                 Model model
    ){

        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);
        model.addAttribute("총 가격",approveResponse.getAmount().getTotal());
//        int AllOriginalTotalOrder = (int) httpSession.getAttribute("AllOriginalTotalOrder");

        model.addAttribute("주문번호", tid);
//        model.addAttribute("AllOriginalTotalOrder", AllOriginalTotalOrder);




        return "/client/content/clientOrder/orderCompleted";
    }

    @GetMapping("/orderFailed") // 주문실패 페이지
    public String orderFailed(){
        return "/client/content/clientOrder/orderFailed";
    }

}

