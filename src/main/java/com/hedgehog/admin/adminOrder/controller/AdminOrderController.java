package com.hedgehog.admin.adminOrder.controller;

import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderForm;
import com.hedgehog.admin.adminOrder.model.service.AdminOrderServiceImpl;
import com.hedgehog.admin.exception.OrderStateUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @GetMapping(value = "/orderSearch")
    public ModelAndView orderSearch(@ModelAttribute AdminOrderForm form,
                                    ModelAndView mv){
        log.info("eventSearch ==================== start");
        log.info(form.toString());

        List<AdminOrderDTO> orderSearch = adminOrderService.searchOrderList(form);
        log.info("=================================eventList" + orderSearch);

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
