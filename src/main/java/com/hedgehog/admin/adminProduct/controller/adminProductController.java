package com.hedgehog.admin.adminProduct.controller;

import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import com.hedgehog.admin.adminProduct.model.service.adminProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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


//    @GetMapping(value = "/productserach")
//    public ModelAndView productsearch(@RequestParam(required = false) String prdCondition,
//                                      @RequestParam(required = false) String serachCondition,
//                                      @RequestParam(required = false) String serachValue,
//                                      @RequestParam(required = false) String prdSerchStartPrice,
//                                      @RequestParam(required = false) String prdSerchEndPrice,
//                                      @RequestParam(required = false) String category1,
//                                      @RequestParam(required = false) String category2,
//                                      @RequestParam(required = false) String proSearchStartDay,
//                                      @RequestParam(required = false) String proSearchEndDay,
//                                      ModelAndView mv){
//        log.info("productsearch ====================== start");
//
//        adminProductForm form = new adminProductForm();
//        form.setPrdCondition(prdCondition);
//        form.setSerachCondition(serachCondition);
//        form.setSerachValue(serachValue);
//        form.setPrdSerchStartPrice(prdSerchStartPrice);
//        form.setPrdSerchEndPrice(prdSerchEndPrice);
//        form.setCategory1(category1);
//        form.setCategory2(category2);
//        form.setProSearchStartDay(proSearchStartDay);
//        form.setProSearchEndDay(proSearchEndDay);
//
//        log.info(form.toString());
//
//        List<adminProductDTO> productList = adminProductServiceImpl.searchProduct(form);
//        log.info("=================================productList" + productList);
//
//        // Add results to the model
//        mv.addObject("productList", productList);
//
//
//
//
//        mv.setViewName("admin/content/product/productSerch");
//
//
//        return mv;
//
//    }

    @GetMapping("/productserach")
    public ModelAndView productList(ModelAndView mv){
        List<adminProductDTO> productList = adminProductServiceImpl.selectAllProductList();
        log.info(productList.toString());

        mv.addObject("productList", productList);
        mv.setViewName("admin/content/product/productserch");

        return mv;
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
