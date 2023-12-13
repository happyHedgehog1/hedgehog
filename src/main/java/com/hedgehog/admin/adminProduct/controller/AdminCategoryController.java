package com.hedgehog.admin.adminProduct.controller;

import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.service.AdminProductServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/category")
public class AdminCategoryController {
    private final AdminProductServiceImpl adminProductServiceImpl;

    public AdminCategoryController(AdminProductServiceImpl adminProductServiceImpl) {
        this.adminProductServiceImpl = adminProductServiceImpl;
    }


    @GetMapping("/categoryDetail")
    public String handleCategoryClick(@RequestParam("categoryCode") String categoryCode) {
        // categoryCode를 이용하여 원하는 동작 수행
        List<AdminProductDTO> productDTO = adminProductServiceImpl.categoryDetail(categoryCode);

        System.out.println("Clicked category with code: " + categoryCode);
        return "admin/content/product/categoryAdd";  // 실제로 사용하는 뷰로 변경
    }

    /**
     * 상분 분류 페이지 연결 메소드
     * @return
     */
    @GetMapping(value = "/categoryAdd")
    public ModelAndView categoryadd(@ModelAttribute AdminCategoryDTO category,
                                        ModelAndView mv){
        log.info("categoryadd ==================== start");
        log.info(category.toString());

        List<AdminCategoryDTO> categoryList = adminProductServiceImpl.categoryList(category);
        log.info("=================================category" + categoryList);

        int totalResult = categoryList.size();


        mv.addObject("categoryList", categoryList);
        mv.addObject("totalResult", totalResult);
        mv.setViewName("admin/content/product/categoryAdd");



        return mv;
    }
}
