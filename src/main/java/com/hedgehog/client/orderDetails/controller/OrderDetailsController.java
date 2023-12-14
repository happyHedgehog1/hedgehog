package com.hedgehog.client.orderDetails.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderListDTO;
import com.hedgehog.client.orderDetails.model.service.OrderDetailsService;
import com.hedgehog.common.paging.orderDetailsPaging.OrderDetailsPagenation;
import com.hedgehog.common.paging.orderDetailsPaging.OrderDetailsSelectCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/myshop")
@Slf4j
@AllArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @GetMapping("/orderDeliveryInfo")
    public ModelAndView orderDeliveryInfo(@AuthenticationPrincipal LoginDetails loginDetails,
                                          @RequestParam(required = false, defaultValue = "0") String state,
                                          @RequestParam(required = false) LocalDate dateStart,
                                          @RequestParam(required = false) LocalDate dateEnd,
                                          @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                          ModelAndView mv) {
        log.info("");
        log.info("");
        log.info("orderDeliveryInfo : OrderDetailsController..... start");
        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
        int userCode = loginUserDTO.getUserCode();
        if (dateStart == null && dateEnd == null) {
            // 맨 처음 접속했을때는 3개월 이내의 정보를 검색한다.
            dateStart = LocalDate.now().minusMonths(3);
            dateEnd = LocalDate.now();
        }
        if (dateEnd == null) {
            // 만약 끝날짜가 null 이라면. 확실히 오늘 날짜까지
            // 즉 처음 접속한게 아니지만. 그래도 끝날짜가 null이었다면.
            dateEnd = LocalDate.now();
        }
        if (dateStart == null) {
            // 처음접속한게 아니지만. 시작날짜가 null인 경우 전체 검색이므로 2000년 1월 1일로 선택한다.
            dateStart = LocalDate.of(2000, 1, 1);
        }

        log.info("orderDeliveryInfo ===== 주문상태: " + state);
        log.info("orderDeliveryInfo ===== 시작날짜: " + dateStart);
        log.info("orderDeliveryInfo ===== 끝날짜: " + dateEnd);
        /*이 위치에 오면 처음에 입력받은 값이 null이라고 할지라도 값이 모두 생긴다.*/
        OrderDTO order = new OrderDTO(state, dateStart, dateEnd);
        log.info("현재검색조건...order : " + order);
        String info = "orderDeliveryInfo";
        int totalCount = orderDetailsService.selectTotalCountOrderInfo(userCode, order, info);
        log.info("orderDeliveryInfo : OrderDetailsController...조건에 맞는 게시글 수... :  " + totalCount);

        /*한 페이지에 5개*/
        int limit = 5;
        /*한번에 페이징 버튼 5개*/
        int buttonAmount = 5;

        OrderDetailsSelectCriteria orderDetailsSelectCriteria = OrderDetailsPagenation.getOrderDetailsSelectCriteria(pageNo, totalCount, limit, buttonAmount, order);
        log.info("");
        log.info("");
        log.info("orderDeliveryInfo : OrderDetailsController..... orderDetailsSelectCriteria : " + orderDetailsSelectCriteria);

        List<OrderListDTO> orderList = orderDetailsService.selectOrderInfoList(userCode, orderDetailsSelectCriteria, info);
        log.info("orderDeliveryInfo : OrderDetailsController ... orderList : " + orderList);
        mv.addObject("orderList", orderList);
        mv.addObject("orderDetailsSelectCriteria", orderDetailsSelectCriteria);
        log.info("orderDeliveryInfo : OrderDetailsController... orderDetailsSelectCriteria" + orderDetailsSelectCriteria);

        mv.setViewName("/client/content/myshop/orderDeliveryInfo");
        mv.addObject("state", state);
        mv.addObject("dateStart", dateStart);
        mv.addObject("dateEnd", dateEnd);
        return mv;
    }

    @GetMapping("/exchangePaybackInfo")
    public ModelAndView exchangePaybackInfo(@AuthenticationPrincipal LoginDetails loginDetails,
                                            @RequestParam(required = false, defaultValue = "0") String state,
                                            @RequestParam(required = false) LocalDate dateStart,
                                            @RequestParam(required = false) LocalDate dateEnd,
                                            @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                            ModelAndView mv) {
        log.info("");
        log.info("");
        log.info("exchangePaybackInfo : OrderDetailsController..... start");
        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
        int userCode = loginUserDTO.getUserCode();
        if (dateStart == null && dateEnd == null) {
            // 맨 처음 접속했을때는 3개월 이내의 정보를 검색한다.
            dateStart = LocalDate.now().minusMonths(3);
            dateEnd = LocalDate.now();
        }
        if (dateEnd == null) {
            // 만약 끝날짜가 null 이라면. 확실히 오늘 날짜까지
            // 즉 처음 접속한게 아니지만. 그래도 끝날짜가 null이었다면.
            dateEnd = LocalDate.now();
        }
        if (dateStart == null) {
            // 처음접속한게 아니지만. 시작날짜가 null인 경우 전체 검색이므로 2000년 1월 1일로 선택한다.
            dateStart = LocalDate.of(2000, 1, 1);
        }

        log.info("exchangePaybackInfo ===== 주문상태: " + state);
        log.info("exchangePaybackInfo ===== 시작날짜: " + dateStart);
        log.info("exchangePaybackInfo ===== 끝날짜: " + dateEnd);
        /*이 위치에 오면 처음에 입력받은 값이 null이라고 할지라도 값이 모두 생긴다.*/
        OrderDTO order = new OrderDTO(state, dateStart, dateEnd);
        log.info("현재검색조건...order : " + order);
        String info = "exchangePaybackInfo";
        int totalCount = orderDetailsService.selectTotalCountOrderInfo(userCode, order, info);
        log.info("exchangePaybackInfo : OrderDetailsController...조건에 맞는 게시글 수... :  " + totalCount);

        /*한 페이지에 5개*/
        int limit = 5;
        /*한번에 페이징 버튼 5개*/
        int buttonAmount = 5;

        OrderDetailsSelectCriteria orderDetailsSelectCriteria = OrderDetailsPagenation.getOrderDetailsSelectCriteria(pageNo, totalCount, limit, buttonAmount, order);
        log.info("");
        log.info("");
        log.info("exchangePaybackInfo : OrderDetailsController..... orderDetailsSelectCriteria : " + orderDetailsSelectCriteria);

        List<OrderListDTO> orderList = orderDetailsService.selectOrderInfoList(userCode, orderDetailsSelectCriteria, info);
        log.info("exchangePaybackInfo : OrderDetailsController ... orderList : " + orderList);
        mv.addObject("orderList", orderList);
        mv.addObject("orderDetailsSelectCriteria", orderDetailsSelectCriteria);
        log.info("exchangePaybackInfo : OrderDetailsController... orderDetailsSelectCriteria" + orderDetailsSelectCriteria);

        mv.setViewName("/client/content/myshop/exchangePaybackInfo");
        mv.addObject("state", state);
        mv.addObject("dateStart", dateStart);
        mv.addObject("dateEnd", dateEnd);
        return mv;
    }

    @GetMapping("/orderDetails")
    public String orderDetails() {
        return "/client/content/myshop/orderDetails";
    }
}
