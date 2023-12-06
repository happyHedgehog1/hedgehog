package com.hedgehog.client.auth.controller;

import com.hedgehog.client.auth.model.service.AuthServiceImpl;
import com.hedgehog.common.common.exception.UserCertifiedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/auth")
@Slf4j // 롬복 기능. 로거 자동 생성
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final AuthServiceImpl authService;

    public AuthController(PasswordEncoder passwordEncoder, AuthServiceImpl authService) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "client/content/auth/login.html";
    }

    @GetMapping("/regist")
    public String regist() {
        return "/client/content/auth/regist";
    }

    @PostMapping("/regist")
    public String registUser() {
        return "";
    }

    @PostMapping(value = "/checkId", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkId(@RequestParam String userId, Model model) {
        Map<String, Object> response = new HashMap<>();
        boolean isDuplicated = authService.selectUserById(userId);
        response.put("result", isDuplicated ? "fail" : "success");
        model.addAttribute("isIdDuplicated", isDuplicated);
        return response;
    }

    @PostMapping(value = "/checkEmail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam String email) throws UserCertifiedException {
        Map<String, Object> response = new HashMap<>();
        boolean isEmailExist = authService.selectMemberByEmail(email); // Member 부분에서
        if (!isEmailExist) {
            int min = 100000;
            int max = 1000000;
            int certifiedCode = authService.selectCertifiedNumber(String.valueOf(new Random().nextInt(max - min) + min));
            System.out.println(certifiedCode);
            response.put("certifiedCode", certifiedCode);
        }
        response.put("result", isEmailExist ? "fail" : "success");
        return response;
    }

    @PostMapping(value = "/emailCertify", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam int inputCertifiedCode,
                                          @RequestParam String certifiedKey,
                                          Model model) {
        Map<String, Object> response = new HashMap<>();
        boolean isDuplicated = authService.certifyEmail(inputCertifiedCode, certifiedKey); // Member 부분에서

        response.put("result", isDuplicated ? "fail" : "success");
        model.addAttribute("isEmailDuplicated", isDuplicated);
        return response;
    }


    @GetMapping("/searchId")
    public String searchId() {
        return "/client/content/auth/searchId.html";
    }

    @GetMapping("/searchPassword")
    public String searchPassword() {
        return "/client/content/auth/searchPassword.html";
    }
}

