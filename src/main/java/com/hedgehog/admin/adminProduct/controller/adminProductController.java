package com.hedgehog.admin.adminProduct.controller;
import com.google.gson.Gson;
import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import com.hedgehog.admin.adminProduct.model.service.adminProductServiceImpl;
import com.hedgehog.admin.exception.AdminProductAddException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
@Slf4j
public class adminProductController {

    private final adminProductServiceImpl adminProductServiceImpl;

    public adminProductController(adminProductServiceImpl adminProductService) {
        this.adminProductServiceImpl = adminProductService;
    }

    @PostMapping("/productAdd")
    private String productAdd(@ModelAttribute adminProductDTO product, RedirectAttributes rttr) throws AdminProductAddException {

        log.info("=============productAdd 시작~~~~~~~~~");

        adminProductServiceImpl.productAdd(product);

        rttr.addFlashAttribute("message", "상품 등록에 성공하였습니다.");

        log.info("=============product 끗~~~~~~~~~~~~~~~");
        return "redirect:admin/content/product/productAdd";
    }



    /**
     * 상품 조회하는 메소드
     * @param form html에서 form 데이터로 전달받은 객체를 선언한 DTO
     * @return 조회된 리스트, 총 상품수, 판매중인 상품수, 판매중지 상태인 상품수를 반환
     */
    @GetMapping(value = "/productserach")
    public ModelAndView productsearch(@ModelAttribute adminProductForm form) {
        log.info("productsearch ====================== start");

        log.info(form.toString());

        List<adminProductDTO> productList = adminProductServiceImpl.searchProduct(form);
        log.info("=================================productList" + productList);

        int totalResult = productList.size();
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < productList.size(); i++) {
            char orderableStatus = productList.get(i).getOrderableStatus();
            log.info(String.valueOf(orderableStatus));

            if ('Y' == orderableStatus) {
                countY++;

            }
            if('N' == orderableStatus){
                countN++;
            }

        }
        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);

        ModelAndView modelAndView = new ModelAndView("admin/content/product/productSerch");
        modelAndView.addObject("productList", productList); // 모델에 productList를 추가
        modelAndView.addObject("totalResult", totalResult);
        modelAndView.addObject("countY", countY);
        modelAndView.addObject("countN", countN);



        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }



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
