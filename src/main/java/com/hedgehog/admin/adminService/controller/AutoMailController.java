package com.hedgehog.admin.adminService.controller;

import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.admin.adminService.model.service.AdminAutoMailServiceImpl;
import com.hedgehog.admin.exception.AdminProductAddException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/autoMailModify")
public class AutoMailController {

    private final AdminAutoMailServiceImpl autoMail;

    public AutoMailController(AdminAutoMailServiceImpl autoMail) {
        this.autoMail = autoMail;
    }



    @GetMapping("/previewMail")
    public String previewMail(@RequestParam int mailCode, Model model){
        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~mailCode : {}", mailCode);

        AdminAutoMailDTO mailDTO = autoMail.previewMail(mailCode);

        model.addAttribute(mailDTO);


        return "admin/content/Service/mailViewport";

    }

    /**
     * 메일 수정하는 페이지에 기존에 DB에 있는 내용 불러오는 메소드     *
     * @param mailCode
     * @param model
     * @return
     */
    @GetMapping("/modifyMailPage")
    public String modifyMailPage(@RequestParam int mailCode, Model model){
        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~mailCode : {}", mailCode);

        AdminAutoMailDTO mailDTO = autoMail.previewMail(mailCode);

        model.addAttribute(mailDTO);


        return "admin/content/Service/mailModify";

    }

    /**
     * 메일 수정하는 메소드
     * @param mailDTO
     * @param model
     * @return
     * @throws AdminProductAddException
     */
    @PostMapping("/modifyMail")
    public String modifyMail(@ModelAttribute AdminAutoMailDTO mailDTO,
                            Model model) throws AdminProductAddException {

        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~mailDTO : {}", mailDTO);

        autoMail.modifyMail(mailDTO);

        model.addAttribute(mailDTO.getFormCode());



        return "admin/content/Service/mailModify";

    }

}
