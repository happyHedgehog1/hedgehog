package com.hedgehog.admin.adminOrder.controller;

import com.hedgehog.admin.adminMember.model.dto.AdminCustomerDTO;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderForm;
import com.hedgehog.admin.adminOrder.model.service.AdminOrderServiceImpl;
import com.hedgehog.admin.exception.OrderStateUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/order")
public class AdminOrderController {

    private final AdminOrderServiceImpl adminOrderService;

    public AdminOrderController(AdminOrderServiceImpl adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    /**
     * 교환신청 메소드
     * @param orderCode
     * @param cause
     * @param rttr
     * @return
     */
    @PostMapping(value = "/exchange")
    private String exchange(@RequestParam("orderCode") int orderCode,
                            @RequestParam("cause") String cause, Model model,
                            RedirectAttributes rttr) throws OrderStateUpdateException {
        log.info("");
        log.info("");
        log.info("exchange~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~orderCode : {}", orderCode);
        log.info("~~~~~~~~~~~~~~~~cause : {}", cause);

        AdminOrderDTO orderDTO = new AdminOrderDTO();
        orderDTO.setOrderCode(orderCode);
        orderDTO.setCause(cause);
        orderDTO.setState("6");

        adminOrderService.exchange(orderDTO);

        AdminOrderDTO orderDetail = adminOrderService.orderDetail(orderCode);
        model.addAttribute("orderDetail", orderDetail);


        rttr.addFlashAttribute("message", "교환신청이 완료되었습니다.");

        return "redirect:/order/orderDetail?orderCode=" + orderCode;
    }

    /**
     * 환불신청 메소드
     * @param orderCode
     * @param cause
     * @param model
     * @param rttr
     * @return
     * @throws OrderStateUpdateException
     */
    @PostMapping(value = "/refund")
    private String refund(@RequestParam("orderCode") int orderCode,
                            @RequestParam("cause") String cause, Model model,
                            RedirectAttributes rttr) throws OrderStateUpdateException {
        log.info("");
        log.info("");
        log.info("exchange~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~orderCode : {}", orderCode);
        log.info("~~~~~~~~~~~~~~~~cause : {}", cause);

        AdminOrderDTO orderDTO = new AdminOrderDTO();
        orderDTO.setOrderCode(orderCode);
        orderDTO.setCause(cause);
        orderDTO.setState("7");

        adminOrderService.refund(orderDTO);

        AdminOrderDTO orderDetail = adminOrderService.orderDetail(orderCode);
        model.addAttribute("orderDetail", orderDetail);


        rttr.addFlashAttribute("message", "환불신청이 완료되었습니다.");

        return "redirect:/order/orderDetail?orderCode=" + orderCode;
    }

    /**
     * 주문내역 상세창 내용 불러오는 메소드
     * @param orderCode
     * @param model
     * @return
     */
    @GetMapping(value = "/orderDetail")
    private String orderDetail(@RequestParam int orderCode, Model model){
        log.info("");
        log.info("");
        log.info("selectProductDetail~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~orderCode : {}", orderCode);

        AdminOrderDTO orderDetail = adminOrderService.orderDetail(orderCode);
        log.info("~~~~~~~~~~~~~~~~orderDetail : {}", orderDetail);


        model.addAttribute("orderDetail", orderDetail);
        return "admin/content/order/orderDetail";

    }

    /**
     * 주문내역창에서 일괄처리 적용하는 메소드
     *
     * @param selectedOrderCodes
     * @param selectedState
     * @param rttr
     * @return
     * @throws OrderStateUpdateException
     */
    @PostMapping(value = "/stateUpdate")
    private String orderStateUpdate(@RequestParam("resultCheckbox") List<String> selectedOrderCodes,
                                    @RequestParam("selectCommit") String selectedState,
                                    RedirectAttributes rttr) throws OrderStateUpdateException {

        log.info("*********************** orderStateUpdate");
        log.info("*********************** selectedOrderCodes"+selectedOrderCodes);
        log.info("*********************** selectedState"+selectedState);


        for(int i =0; i < selectedOrderCodes.size(); i++){
            if("on".equals(selectedOrderCodes.get(i)) || selectedOrderCodes.get(i).isEmpty()){
                continue;
            }else {
                int orderCode = Integer.parseInt(selectedOrderCodes.get(i));
                log.info("orderCode**********************" + orderCode);
                AdminOrderDTO orderDTO = new AdminOrderDTO();
                orderDTO.setOrderCode(orderCode);
                orderDTO.setState(selectedState);


                log.info("order**********************" + orderDTO);
                adminOrderService.orderStateUpdate(orderDTO);
            }
        }
        rttr.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "redirect:/order/orderSearch";
    }

    /**
     * 주문내역 조회 메소드
     * @param form
     * @param mv
     * @return
     */
    @GetMapping(value = "/orderSearch")
    public ModelAndView orderSearch(@ModelAttribute AdminOrderForm form,
                                    ModelAndView mv){
        log.info("eventSearch ==================== start");
        log.info(form.toString());

        List<AdminOrderDTO> orderSearch = adminOrderService.searchOrderList(form);
        log.info("=================================orderSearch" + orderSearch);

        int totalResult = orderSearch.size();


        mv.addObject("orderSearch", orderSearch);
        mv.addObject("totalResult", totalResult);
        mv.setViewName("admin/content/order/order");

        log.info("totalResult" + String.valueOf(totalResult));


        return mv;
    }


    /**
     *
     * @return 주문내역 페이지 연결 메소드
     */
    @GetMapping("/orderPage")
    public String order(){
        return "admin/content/order/order.html";
    }
}
