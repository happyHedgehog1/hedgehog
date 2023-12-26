package com.hedgehog.client.main.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.list.dto.ProductListDTO;
import com.hedgehog.client.main.model.service.MainProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    private MainProductService mainProductService;

    public MainController(MainProductService mainProductService) {
        this.mainProductService = mainProductService;
    }
    @GetMapping(value = {"/", "/main"})
    public String defaultLocation(RedirectAttributes redirectAttributes, @AuthenticationPrincipal LoginDetails loginDetails , Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("권한: " + authentication.getAuthorities());


        List<ProductListDTO> bestProduct = mainProductService.selectBestProduct();  /* 상품리스트 */
        System.out.println("화킨======="+bestProduct);

        model.addAttribute("bestProduct", bestProduct);


        System.out.println("bestProduct===="+bestProduct);

        return "client/content/main/main";
    }



}
