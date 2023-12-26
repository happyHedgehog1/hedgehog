package com.hedgehog.client.main.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.main.model.dto.MainProduct;
import com.hedgehog.client.main.model.service.MainProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    @GetMapping(value = {"/", "/main"})
    public String defaultLocation(RedirectAttributes redirectAttributes, @AuthenticationPrincipal LoginDetails loginDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("권한: " + authentication.getAuthorities());

        return "client/content/main/main";
    }



}
