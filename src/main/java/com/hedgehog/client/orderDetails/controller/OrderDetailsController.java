package com.hedgehog.client.orderDetails.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderDetailsCollect;
import com.hedgehog.client.orderDetails.model.dto.OrderDetailsDTO;
import com.hedgehog.client.orderDetails.model.dto.OrderListDTO;
import com.hedgehog.client.orderDetails.model.service.OrderDetailsService;
import com.hedgehog.common.common.exception.UserCertifiedException;
import com.hedgehog.common.common.exception.UserEmailNotFoundException;
import com.hedgehog.common.paging.orderDetailsPaging.OrderDetailsPagenation;
import com.hedgehog.common.paging.orderDetailsPaging.OrderDetailsSelectCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
        OrderDTO order = new OrderDTO(state, dateStart, dateEnd.atStartOfDay().plusDays(1).minusSeconds(1));
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

        /*이부분에서 일주일전, 한달전, 세달전, 여섯달 전에 대한 변수를 반환한다.*/
        mv.addObject("now", LocalDate.now());
        mv.addObject("date7", LocalDate.now().minusDays(7));
        mv.addObject("date30", LocalDate.now().minusMonths(1));
        mv.addObject("date90", LocalDate.now().minusMonths(3));
        mv.addObject("date180", LocalDate.now().minusMonths(6));
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
        OrderDTO order = new OrderDTO(state, dateStart, dateEnd.atStartOfDay().plusDays(1).minusSeconds(1));
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

        /*이부분에서 일주일전, 한달전, 세달전, 여섯달 전에 대한 변수를 반환한다.*/
        mv.addObject("now", LocalDate.now());
        mv.addObject("date7", LocalDate.now().minusDays(7));
        mv.addObject("date30", LocalDate.now().minusMonths(1));
        mv.addObject("date90", LocalDate.now().minusMonths(3));
        mv.addObject("date180", LocalDate.now().minusMonths(6));
        return mv;
    }

    @PostMapping("/memberOrderDetails")
    @ResponseBody
    public String memberOrderDetails(@AuthenticationPrincipal LoginDetails loginDetails,
                                     @RequestParam int orderCode) {
        int userCode = loginDetails.getLoginUserDTO().getUserCode();
        log.info("주문내역을 찾으려는데... 계정코드 : " + userCode);
        boolean result = orderDetailsService.isYourOrder(userCode, orderCode);
        log.info("주문내역이 지금 내 계정정보와 같냐... : " + result);

        return result == true ? "success" : "fail";
    }

    @GetMapping("/orderDetails")
    public String orderDetailsInfo(@AuthenticationPrincipal LoginDetails loginDetails,
                                   @RequestParam int orderCode,
                                   @RequestParam(required = false) String email,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        if (loginDetails == null) {
            log.info("잘못된 접근이라 메인으로 돌아갑니다.");
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다. 메인으로 돌아갑니다.");
            return "redirect:/";
        }
        int userCode = loginDetails.getLoginUserDTO().getUserCode();
        boolean result = orderDetailsService.isYourOrder(userCode, orderCode);
        if (!result) {
            log.info("계정정보가 달라서 메인으로 돌아갑니다.");
            return "redirect:/";
        }

        OrderDetailsCollect orderDetailsCollect = orderDetailsService.getOrderDetails(orderCode);

        log.info("orderDetailsInfo : OrderDetailsController ... orderDetailsCollect : \n" + orderDetailsCollect);


        model.addAttribute("orderDetails", orderDetailsCollect);
        int sumCostPrice = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getCostPrice() * orderDetail.getCount()));
        model.addAttribute("sumCostPrice", sumCostPrice);
        int sumReducedPrice = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getReducedPrice() * orderDetail.getCount()));
        model.addAttribute("sumReducedPrice", sumReducedPrice);
        int sumDeliveryCharge = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getDeliveryCharge() * orderDetail.getCount()));
        model.addAttribute("sumDeliveryCharge", sumDeliveryCharge);
        int sumPointCharge = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getPointCharge() * orderDetail.getCount()));
        model.addAttribute("sumPointCharge", sumPointCharge);
        model.addAttribute("finalPrice", sumCostPrice - sumReducedPrice - orderDetailsCollect.getPointUsage() + sumDeliveryCharge);
        int sumReviewPoint = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getReviewPoint()));
        model.addAttribute("sumReviewPoint", sumReviewPoint);

        return "/client/content/myshop/orderDetails";
    }

    @PostMapping("/orderDetails")
    public String guestOrderDetails(@RequestParam(required = false) Integer orderCode,
                                    @RequestParam(required = false) String email,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        if (orderCode == null || email == null) {
            redirectAttributes.addFlashAttribute("message", "주문번호 또는 이메일을 입력해주세요.");
            return "redirect:/myshop/guestOrderSearch";
        }
        Integer newOrderCode = orderDetailsService.selectOrderCode(orderCode, email); // 현재 주문번호에 알맞는 이메일이 있는지. 그리고 같은지
        // 물론 orderCode 를 넣어서 orderCode를 반환받는게 이상하지만, 이메일까지 들어가므로 null이 뜰 가능성이 충분히 있다.
        if (newOrderCode == null) {
            redirectAttributes.addFlashAttribute("message", "조건에 맞는 비회원이 없습니다.\n다시입력해주세요.");
            return "redirect:/myshop/guestOrderSearch";
        }
        log.info("newOrderCode: " + newOrderCode);
        if (newOrderCode != orderCode) {
            log.info("계정정보가 달라서 메인으로 돌아갑니다.");
            redirectAttributes.addFlashAttribute("message", "계정정보가 달라서 메인으로 돌아갑니다.");
            return "redirect:/";
        }
        OrderDetailsCollect orderDetailsCollect = orderDetailsService.getOrderDetails(orderCode);

        log.info("orderDetailsInfo : OrderDetailsController ... orderDetailsCollect : \n" + orderDetailsCollect);

        model.addAttribute("orderDetails", orderDetailsCollect);
        int sumCostPrice = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getCostPrice() * orderDetail.getCount()));
        model.addAttribute("sumCostPrice", sumCostPrice);
        int sumReducedPrice = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getReducedPrice() * orderDetail.getCount()));
        model.addAttribute("sumReducedPrice", sumReducedPrice);
        int sumDeliveryCharge = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getDeliveryCharge() * orderDetail.getCount()));
        model.addAttribute("sumDeliveryCharge", sumDeliveryCharge);
        int sumPointCharge = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getPointCharge() * orderDetail.getCount()));
        model.addAttribute("sumPointCharge", sumPointCharge);
        model.addAttribute("finalPrice", sumCostPrice - sumReducedPrice - orderDetailsCollect.getPointUsage() + sumDeliveryCharge);
        int sumReviewPoint = orderDetailsCollect
                .getOrderDetailsList()
                .stream()
                .collect(Collectors.summingInt((orderDetail) -> orderDetail.getReviewPoint()));
        model.addAttribute("sumReviewPoint", sumReviewPoint);

        return "/client/content/myshop/orderDetails";
    }
}
