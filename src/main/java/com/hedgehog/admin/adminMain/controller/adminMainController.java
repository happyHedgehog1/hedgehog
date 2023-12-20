package com.hedgehog.admin.adminMain.controller;

import com.hedgehog.admin.adminMain.model.dto.AdminDailyVisitorsDTO;
import com.hedgehog.admin.adminMain.model.dto.AdminMainStatisticsDTO;
import com.hedgehog.admin.adminMain.model.service.AdminDailyVisitorsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/adminmain")
@Slf4j
public class adminMainController {

    private final AdminDailyVisitorsServiceImpl adminDailyVisitorsServiceImpl;


    public adminMainController(AdminDailyVisitorsServiceImpl adminDailyVisitorsServiceImpl) {
        this.adminDailyVisitorsServiceImpl = adminDailyVisitorsServiceImpl;

    }

//     //당일 방문자
//    @GetMapping("/dailyVisitors")
//    public String dailyVisitors(Model model){
//        List<AdminDailyVisitorsDTO> dailyVisitorsDTO = adminDailyVisitorsServiceImpl.dailyVisitors();
//        int visitorCount = dailyVisitorsDTO.size();
//        model.addAttribute("dailyVisitorsDTO",dailyVisitorsDTO);
//        model.addAttribute("dailyVisitors", visitorCount);
//        log.info("=================dailyVisitorsDTO" +dailyVisitorsDTO);
//        return "admin/content/main/admin-main";
//    }

    /**
     *
     * @return 관리자 메인페이지 연결 메소드
     */
    @GetMapping("/main")
    public ModelAndView adminmain(Model model){
        List<AdminDailyVisitorsDTO> dailyVisitorsDTO = adminDailyVisitorsServiceImpl.dailyVisitors();

        int visitorCount = dailyVisitorsDTO.size();
        AdminMainStatisticsDTO adminMainStatisticsDTO = adminDailyVisitorsServiceImpl.dailySales();

        log.info("=================visitorCount" +visitorCount);
        log.info("=================dailyVisitorsDTO" +dailyVisitorsDTO);


        ModelAndView mv = new ModelAndView();
        model.addAttribute("dailyVisitors", visitorCount);
        model.addAttribute("dailySales", adminMainStatisticsDTO);
        mv.setViewName("admin/content/main/admin-main");

        return mv;
    }



}
