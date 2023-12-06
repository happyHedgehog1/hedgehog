package com.hedgehog.admin.adminProduct.controller;

import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import com.hedgehog.admin.adminProduct.model.service.adminProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
@Slf4j
public class adminProductController {

    private final adminProductServiceImpl adminProductServiceImpl;

    public adminProductController(adminProductServiceImpl adminProductService) {
        this.adminProductServiceImpl = adminProductService;
    }


    @GetMapping(value = "/productserach")
    public ModelAndView productsearch(@ModelAttribute("adminProductForm") adminProductForm form) {
        log.info("productsearch ====================== start");

        log.info(form.toString());

        List<adminProductDTO> productList = adminProductServiceImpl.searchProduct(form);
        log.info("=================================productList" + productList);

        ModelAndView modelAndView = new ModelAndView("admin/content/product/productSerch");
        modelAndView.addObject("productList", productList); // 모델에 productList를 추가

        return modelAndView;
    }
//
//    @GetMapping("/productserach")
//    public ModelAndView productList(ModelAndView mv){
//        List<adminProductDTO> productList = adminProductServiceImpl.selectAllProductList();
//        log.info(productList.toString());
//
//        mv.addObject("productList", productList);
//        mv.setViewName("admin/content/product/productSerch");
//
//        return mv;
//    }




    /**
     * 상품조회 페이지 연결 메소드
     * @return 관리자 상품조회 페이지
     */
//    @GetMapping("/productserachPage")
//    public String productsearch(){ return "admin/content/product/productSerch";}

    /**
     * 상품등록 페이지 연결 메소드
     * @return 관리자 상품등록 페이지
     */
    @GetMapping("/productAdd")
    public String productadd(){ return "admin/content/product/productAdd";}



}
