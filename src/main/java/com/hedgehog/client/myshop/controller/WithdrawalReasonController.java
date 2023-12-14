package com.hedgehog.client.myshop.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.myshop.model.dto.WithdrawalRequestDTO;
import com.hedgehog.client.myshop.model.service.WithdrawalReasonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/myshop/withdrawalReason")
@Slf4j
@AllArgsConstructor
public class WithdrawalReasonController {
    private final WithdrawalReasonService withdrawalReasonService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String withdrawalReason() {
        return "/client/content/myshop/withdrawalReason";
    }

    @PostMapping("/submit")
    public String submitData(@AuthenticationPrincipal LoginDetails loginDetails,
                             @ModelAttribute WithdrawalRequestDTO withdrawalRequest,
                             RedirectAttributes redirectAttributes) {
        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();

        String inputPwd = withdrawalRequest.getUserPwd();
        String loginUserPwd = loginUserDTO.getUserPwd();
        if (passwordEncoder.matches(inputPwd, loginUserPwd)) {
            // 비밀번호가 일치하는 경우
            LocalDateTime commitDateTime = LocalDateTime.now().plusDays(7);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            redirectAttributes.addFlashAttribute("message", "회원탈퇴 신청이 완료되었습니다.\n" +
                    commitDateTime.format(formatter) + " 이전에 들어오시면 탈퇴 신청이 취소됩니다.");

            withdrawalReasonService.insertWithdrawalReason(loginUserDTO.getUserCode(),withdrawalRequest.getSummernoteContent());

            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("message","비밀번호가 일치하지 않습니다.");
            return "redirect:/myshop/withdrawalReason";
        }
        // 1. user_code
        // 2. apply_date 신청시기 -> submit 된 시기
        // 3. cause 원인 이유 -> 3000자 이하
        // 4. 비밀번호 일치여부도 파악해야 한다.
//        redirectAttributes.addFlashAttribute("message", "회원탈퇴신청이 완료되었습니다.<br>여기에 시간 작성");
//        return "redirect:/";
    }
}
