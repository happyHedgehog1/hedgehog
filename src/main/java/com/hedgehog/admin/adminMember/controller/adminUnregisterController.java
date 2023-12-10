package com.hedgehog.admin.adminMember.controller;

import com.hedgehog.admin.adminMember.model.dto.adminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.adminUnregisterForm;
import com.hedgehog.admin.adminMember.model.service.adminUnregisterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/unregister")
@Slf4j
public class adminUnregisterController {

    private final adminUnregisterServiceImpl adminUnregisterServiceimpl;

    public adminUnregisterController(adminUnregisterServiceImpl adminUnregisterServiceimpl) {
        this.adminUnregisterServiceimpl = adminUnregisterServiceimpl;
    }

    @GetMapping("/unregisterSearch")
    public ModelAndView unregister(@ModelAttribute adminUnregisterForm form){
        log.info("unregisterSearch==================start");
        log.info(form.toString());

        List<adminUnregisterDTO> unregisterList = adminUnregisterServiceimpl.selectUnregister(form);
        log.info("unregisterList=======================" + unregisterList);
        int totalResult= unregisterList.size();
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < unregisterList.size(); i++) {
            String state = unregisterList.get(i).getState();
            if ("Y".equals(String.valueOf(state))) {
                countY++;
            }
            if ("N".equals(String.valueOf(state))) {
                countN++;
            }
        }
        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);

        ModelAndView modelAndView = new ModelAndView("admin/content/member/unregister");
        modelAndView.addObject("unregisterList", unregisterList);
        modelAndView.addObject("totalResult", totalResult);
        modelAndView.addObject("countY", countY);
        modelAndView.addObject("countN", countN);

        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }
}
