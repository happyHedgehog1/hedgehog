package com.hedgehog.admin.adminProduct.controller;

import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryForm;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductForm;
import com.hedgehog.admin.adminProduct.model.service.AdminProductServiceImpl;
import com.hedgehog.admin.exception.AdminProductAddException;
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

    @PostMapping(value = "categoryModify")
    public String categoryModify(@ModelAttribute AdminCategoryForm categoryForm) throws AdminProductAddException {

        log.info(String.valueOf(categoryForm));

        adminProductServiceImpl.categoryModify(categoryForm);

        return "redirect:/category/categoryAdd";


    }


    @GetMapping(value = "/categoryDetail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProductDTO> handleCategoryClick(@RequestParam("categoryName") String categoryName) {

        log.info("categoryDetail ==================== start" + categoryName);
        categoryName.trim();


        log.info("categoryDetail ==================== " + categoryName);



        List<AdminProductDTO> productDTO = adminProductServiceImpl.categoryDetail(categoryName);

        log.info("categoryDetail ==================== " + productDTO);
        int totalCount = productDTO.size();
        int stateY = 0;
        int stateN = 0;
        for (int i = 0; i < productDTO.size(); i++) {
            if (productDTO.get(i).getOrderableStatus().equals("Y")) {
                stateY++;
                productDTO.get(i).setPrice(stateY);
                productDTO.get(i).setProductCode(totalCount);
            }
        }
        productDTO.get(0).setOrderableStatus(productDTO.get(0).getCategory().getState());
        log.info("categoryDetail ==================== " + productDTO);
        return productDTO;
    }


    /**
     * 상분 분류 페이지 연결 메소드
     *
     * @return
     */
    @GetMapping(value = "/categoryAdd")
    public ModelAndView categoryadd(@ModelAttribute AdminCategoryDTO category,
                                    ModelAndView mv) {
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


    @GetMapping("/categoryPage")
    public String productAddPage() {
        return "admin/content/product/categoryAdd";
    }
}