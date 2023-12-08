package com.hedgehog.admin.adminManagement.controller;

import com.hedgehog.admin.adminManagement.model.dto.AdminDTO;
import com.hedgehog.admin.adminManagement.model.dto.AdminRegistrationForm;
import com.hedgehog.admin.adminManagement.model.dto.ChangePwdForm;
import com.hedgehog.admin.adminManagement.model.service.AdminManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminManagement")
@Slf4j
public class AdminManagementController {
    private final AdminManagementService adminManagementService;
    private final PasswordEncoder passwordEncoder;

    public AdminManagementController(AdminManagementService adminManagementService, PasswordEncoder passwordEncoder) {
        this.adminManagementService = adminManagementService;
        this.passwordEncoder = passwordEncoder;
    }



    /**
     * @return 관리자 관리 페이지 연결 메소드
     */
    @GetMapping("/adminManagement")
    public String adminManagement(Model model) {
        List<AdminDTO> adminList = adminManagementService.getAdminList();

        model.addAttribute("adminList", adminList);
        return "admin/content/adminManagement/adminManagement";
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam int userCode) {
        boolean success = adminManagementService.deleteAdmin(userCode);
        if (success) {
            return new ResponseEntity<>("삭제가 완료되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("삭제를 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/registAdmin")
    public String registAdmin(@ModelAttribute AdminRegistrationForm registrationForm, Model model) {
        AdminRegistrationForm newForm = new AdminRegistrationForm(registrationForm.getAdminAddId(),
                passwordEncoder.encode(registrationForm.getAdminAddPass()),
                registrationForm.getAdminAddName());
        boolean isSuccess = adminManagementService.registAdmin(newForm);
        if (isSuccess) {
            return "redirect:/adminManagement/adminManagement";
        } else {
            model.addAttribute("errorMessage", "등록에 실패했습니다. 유효한 데이터를 입력해주세요.");
            return "forward:/adminManagement/adminManagement";
        }
    }
    @PostMapping("/changePassword")
    public String adminManagement(@ModelAttribute ChangePwdForm pwdForm, Model model) {
        ChangePwdForm newPwdForm = new ChangePwdForm(pwdForm.getUserCode(), passwordEncoder.encode(pwdForm.getAdminUpdatePass()));
        System.out.println(newPwdForm);
        boolean success = adminManagementService.updatePwd(newPwdForm);
        if(success) {
            return "redirect:/adminManagement/adminManagement";
        }else{
            model.addAttribute("errorMessage","비밀번호 변경에 실패했습니다.");
            return "forward:/adminManagement/adminManagement";
        }
    }
}
