package com.hedgehog.admin.adminEvent.controller;

import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;
import com.hedgehog.admin.adminEvent.model.service.AdminEventServiceImpl;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/event")
public class AdminEventListController {

    private final AdminEventServiceImpl adminEventService;

    public AdminEventListController(AdminEventServiceImpl adminEventService) {
        this.adminEventService = adminEventService;
    }

    /**
     * 이벤트 수정을 위해 정보 비동기로 가져오는 메소드
     * @param form
     * @return
     */
    @PostMapping(value = "/modify", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, String> modify(@RequestBody AdminEventForm form, RedirectAttributes rttr) {
        Map<String , String> response = new HashMap<>();
        try {
            String post_code = String.valueOf(form.getPost_code());
            String eventName = form.getEventName();
            String searchStartDay = form.getSearchStartDay();
            String status = form.getStatus();
            String searchEndDay = form.getSearchEndDay();
            List<String> allProductCodes = form.getAllProductCodes();
            List<String> productCode = form.getProductCode();
            double price = form.getPrice() * 0.01;
            form.setPrice(price);

            log.info("");
            log.info("");
            log.info("*************************" + post_code);
            log.info("*************************" + eventName);
            log.info("*************************" + status);
            log.info("*************************" + searchStartDay);
            log.info("*************************" + searchEndDay);
            log.info("*************************" + price);
            log.info("*************************" + allProductCodes);
            log.info("*************************" + productCode);
            log.info("*************************" + form);

            adminEventService.modifyEvent(form);
            response.put("success", String.valueOf(true));
        }catch (Exception e){
            response.put("success", String.valueOf(false));
        }

            return response;


    }


    /**
     * 이벤트 등록 메소드
     * @param form
     * @return
     */
    @PostMapping(value = "/register", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String , String> registerEvent(@RequestBody AdminEventForm form, RedirectAttributes rttr) {
        Map<String , String> response = new HashMap<>();
        try{

            String eventName = form.getEventName();
            String searchStartDay = form.getSearchStartDay();
            String status = form.getStatus();
            String searchEndDay = form.getSearchEndDay();
            List<String> allProductCodes = form.getAllProductCodes();
            double price = form.getPrice() * 0.01;
            form.setPrice(price);


            log.info("");
            log.info("");
            log.info("*************************" + eventName);
            log.info("*************************" + status);
            log.info("*************************" + searchStartDay);
            log.info("*************************" + searchEndDay);
            log.info("*************************" + price);
            log.info("*************************" + allProductCodes);
            log.info("*************************" + form);

            adminEventService.updateEventStatus(form);

            response.put("success", String.valueOf(true));
    }catch (Exception e){
        response.put("success", String.valueOf(false));
    }
            return response;
    }

    @PostMapping(value = "/productSearch", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProductDTO> productSearch(@RequestParam("prdKeyword") String prdKeyword,
                                               @RequestParam("searchValue") String searchValue,
                                               @RequestParam("searchStartPrice") String searchStartPrice,
                                               @RequestParam("searchEndPrice") String searchEndPrice,
                                               @RequestParam("subCategoryName") String subCategoryName) {
        log.info("");
        log.info("");
        log.info("*************************" + prdKeyword);
        log.info("*************************" + searchValue);
        log.info("*************************" + searchStartPrice);
        log.info("*************************" + searchEndPrice);
        log.info("*************************" + subCategoryName);

        AdminEventForm form =new AdminEventForm();
        form.setPrdKeyword(prdKeyword);
        form.setSearchValue(searchValue);
        form.setSearchStartPrice(Integer.parseInt(searchStartPrice));
        form.setSearchEndPrice(Integer.parseInt(searchEndPrice));
        if(subCategoryName != "") {
            form.setSubCategoryName(Integer.parseInt(subCategoryName));
        }

        log.info("*************************" + form);



        List<AdminProductDTO> productDTO = adminEventService.searchProduct(form);
        int totalResult = productDTO.size();

        log.info("*************************" + productDTO);
        productDTO.get(0).setReviews(totalResult);


        return productDTO;
    }




    @GetMapping("/eventModify")
    private String eventDetail(@RequestParam("postCode") int postCode, Model model){
        log.info("eventDetail ==================== start");
        log.info("postCode ==================== " + postCode);

        List<AdminEventDTO> adminEventDTO = adminEventService.eventDetail(postCode);

        log.info("adminEventDTO ==================== " + adminEventDTO);
        model.addAttribute("event", adminEventDTO);


        return "admin/content/event/eventModify";

    }

    @GetMapping(value = "/eventListSearch")
    private ModelAndView eventListSearch(@ModelAttribute AdminEventForm form){
        log.info("eventSearch ==================== start");
        log.info(form.toString());

        List<AdminEventDTO> eventList = adminEventService.searchEventList(form);
        log.info("=================================eventList" + eventList);

        int totalResult = eventList.size();
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < eventList.size(); i++) {
            String orderableStatus = eventList.get(i).getStatus();
            log.info(orderableStatus);

            if (orderableStatus.equals("Y")) {
                countY++;

            }
            if(orderableStatus.equals("N")){
                countN++;
            }

        }
        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);
        for (int i = 0 ; i < eventList.size(); i++){
            int discount = (int) (eventList.get(i).getDiscount() * 100);

            eventList.get(i).setDiscount(discount);

        }

        ModelAndView mv = new ModelAndView("admin/content/event/eventList");
        mv.addObject("eventList", eventList);
        mv.addObject("totalResult", totalResult);
        mv.addObject("countY", countY);
        mv.addObject("countN", countN);

        log.info("totalResult" + String.valueOf(totalResult));


        return mv;
    }
    /**
     *
     * @return 이벤트 목록 페이지 연결 메소드
     */
    @GetMapping("/eventListPage")
    private String eventList(){
        return "admin/content/event/eventList";
    }

    /**
     *
     * @return 이벤트 상품 추가 페이지 연결 메소드
     */
    @GetMapping("/eventProdAdd")
    private String eventProdAdd(){
        return "admin/content/event/eventProdAdd";
    }
}
