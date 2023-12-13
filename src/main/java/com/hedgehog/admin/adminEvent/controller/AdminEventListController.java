package com.hedgehog.admin.adminEvent.controller;

import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;
import com.hedgehog.admin.adminEvent.model.service.AdminEventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/event")
public class AdminEventListController {

    private final AdminEventServiceImpl adminEventService;

    public AdminEventListController(AdminEventServiceImpl adminEventService) {
        this.adminEventService = adminEventService;
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
