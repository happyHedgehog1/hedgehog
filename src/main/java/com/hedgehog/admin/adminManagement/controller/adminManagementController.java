package com.hedgehog.admin.adminManagement.controller;

import com.hedgehog.admin.adminManagement.model.dto.AdminDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adminManagement")
public class adminManagementController {

    /**
     * @return 관리자 관리 페이지 연결 메소드
     */
    @GetMapping("/adminManagement")
    public String adminManagement(Model model) {
        List<AdminDTO> adminList = new ArrayList<>();
        model.addAttribute("adminList", adminList);
        return "admin/content/adminManagement/adminManagement.html";
    }
}
