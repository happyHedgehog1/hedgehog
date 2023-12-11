package com.hedgehog.client.auth.controller;

import com.hedgehog.client.auth.model.dto.MemberDTO;
import com.hedgehog.client.auth.model.dto.RegistrationForm;
import com.hedgehog.client.auth.model.dto.PostDTO;
import com.hedgehog.client.auth.model.service.AuthServiceImpl;
import com.hedgehog.common.common.exception.UserCertifiedException;
import com.hedgehog.common.common.exception.UserRegistPostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/auth")
@Slf4j // 롬복 기능. 로거 자동 생성
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final AuthServiceImpl registService;

    public AuthController(PasswordEncoder passwordEncoder, AuthServiceImpl registService) {
        this.passwordEncoder = passwordEncoder;
        this.registService = registService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "order", required = false) String order, Model model) {
        model.addAttribute("order", order);
        return "client/content/auth/login.html";
    }

    @GetMapping("/regist")
    public ModelAndView registPage(ModelAndView mv) throws UserRegistPostException {
        List<PostDTO> postList = registService.getRegistPosts();
        if (postList.get(0).getPostType().equals("공지사항")) {
            mv.addObject("termsAndConditions", postList.get(0).getContent());
            mv.addObject("privacyPolicy", postList.get(1).getContent());
        } else {
            mv.addObject("termsAndConditions", postList.get(0).getContent());
            mv.addObject("privacyPolicy", postList.get(1).getContent());
        }
        mv.setViewName("/client/content/auth/regist");
        return mv;
    }

    @PostMapping("/regist")
    public String registMember(@ModelAttribute RegistrationForm registrationForm, RedirectAttributes redirectAttributes) {
        System.out.println(registrationForm);
        MemberDTO newMember = new MemberDTO(
                registrationForm.getUserId(),
                passwordEncoder.encode(registrationForm.getUserPwd()),
                registrationForm.getName(),
                registrationForm.getEmail(),
                registrationForm.getPhone(),
                registrationForm.getBirthday(),
                registrationForm.getGender(),
                registrationForm.getHiddenCertifiedKey(),
                registrationForm.getEmailService());

        boolean registrationSuccess = registService.registMember(newMember);

        if (registrationSuccess) {
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/auth/login";
        } else {
            redirectAttributes.addFlashAttribute("message", "알 수 없는 오류로 회원가입에 실패했습니다. 메인화면으로 돌아갑니다.");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/checkId", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkId(@RequestParam String userId) {
        Map<String, Object> response = new HashMap<>();
        boolean isDuplicated = registService.selectUserById(userId);
        response.put("result", isDuplicated ? "fail" : "success");
        return response;
    }

    @PostMapping(value = "/checkEmail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam String email) throws UserCertifiedException {
        Map<String, Object> response = new HashMap<>();
        boolean isEmailExist = registService.selectMemberByEmail(email); // Member 부분에서
        if (!isEmailExist) {
            int min = 100000;
            int max = 1000000;
            int certifiedCode = registService.selectCertifiedNumber(String.valueOf(new Random().nextInt(max - min) + min));
            System.out.println(certifiedCode);
            response.put("certifiedCode", certifiedCode);
        }
        response.put("result", isEmailExist ? "fail" : "success");
        return response;
    }

    @PostMapping(value = "/emailCertify", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam int inputCertifiedCode,
                                          @RequestParam String certifiedKey) {
        Map<String, Object> response = new HashMap<>();
        boolean isDuplicated = registService.certifyEmail(inputCertifiedCode, certifiedKey); // Member 부분에서

        response.put("result", isDuplicated ? "fail" : "success");
        return response;
    }


    @GetMapping("/searchId")
    public String searchIdPage() {
        return "/client/content/auth/searchId.html";
    }

    @GetMapping("/searchPassword")
    public String searchPasswordPage() {
        return "/client/content/auth/searchPassword.html";
    }

    @GetMapping("fail")
    public String loginFall(@RequestParam String message, Model model) {
        model.addAttribute("message", message);
        return "/client/content/auth/fail";
    }
}

