package com.hedgehog.admin.adminMember.controller;

import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterForm;
import com.hedgehog.admin.adminMember.model.service.AdminUnregisterServiceImpl;
import com.hedgehog.admin.exception.OrderStateUpdateException;
import com.hedgehog.admin.exception.UnregistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/unregister")
@Slf4j
public class AdminUnregisterController {

    private final AdminUnregisterServiceImpl adminUnregisterServiceimpl;

    public AdminUnregisterController(AdminUnregisterServiceImpl adminUnregisterServiceimpl) {
        this.adminUnregisterServiceimpl = adminUnregisterServiceimpl;
    }

    @PostMapping(value = "/stateUpdate")
    private String orderStateUpdate(@RequestParam("resultCheckbox") List<String> selectedId,
                                    RedirectAttributes rttr) throws OrderStateUpdateException, UnregistException {

        log.info("*********************** orderStateUpdate");
        log.info("*********************** selectedOrderCodes"+selectedId);


        for(int i =0; i < selectedId.size(); i++){

            int userCode = Integer.parseInt(selectedId.get(i));
                log.info("orderCode**********************" + userCode);
                AdminUnregisterDTO adminUnregisterDTO = new AdminUnregisterDTO();
                adminUnregisterDTO.setUser_code(userCode);


                log.info("order**********************" + adminUnregisterDTO);
                adminUnregisterServiceimpl.causeUpdate(adminUnregisterDTO);
            }

        rttr.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "redirect:/unregister/unregisterSearch";
    }

    @GetMapping("/unregisterSearch")
    public ModelAndView unregister(@ModelAttribute AdminUnregisterForm form){
        log.info("unregisterSearch==================start");
        log.info(form.toString());

        List<AdminUnregisterDTO> unregisterList = adminUnregisterServiceimpl.selectUnregister(form);
        log.info("unregisterList=======================" + unregisterList);
        int totalResult= unregisterList.size();
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < unregisterList.size(); i++) {
            String state = unregisterList.get(i).getState();
            if (unregisterList.get(i).getCause().equals("관리자 탈퇴") || unregisterList.get(i).getCause().equals("강제 탈퇴")) {
                countN++;
            }else {
                countY++;
            }

        }
        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);

        ModelAndView modelAndView = new ModelAndView("admin/content/member/unregister");
        modelAndView.addObject("unregisterList", unregisterList);
        modelAndView.addObject("totalResult", totalResult);
        modelAndView.addObject("countY", countY);
        modelAndView.addObject("countN", countN);


        return modelAndView;
    }
}
