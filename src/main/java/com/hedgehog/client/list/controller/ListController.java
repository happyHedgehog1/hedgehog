package com.hedgehog.client.list.controller;
import com.hedgehog.client.list.dto.ProductListDTO;
import com.hedgehog.client.list.service.ProductListService;
import com.hedgehog.common.paging.Pagenation;
import com.hedgehog.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/list")
@Slf4j
public class ListController {



//    @GetMapping("/productList/{category}")
//    public String getCategory(@PathVariable String category , Model model){
//
//        model.addAttribute("category", category);
//        return "/client/content/list/productList";
//    }


    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    private final ProductListService productListService;

    public ListController(ProductListService productListService) {

        this.productListService = productListService;

    }

    @GetMapping("/productList/{type}")
    public ModelAndView selectProductList(ModelAndView mv, @PathVariable String type) {

        log.info("[ListController] ========================================================= start");

        /* 페이징처리 */

        int totalCount = productListService.selectTotalPageCount(type);

        log.info("컨트롤러의 페이지총갯수==================== {}" , totalCount);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;


        /* 게시물 조회 */

        List<ProductListDTO> productList = productListService.selectProductList(type);

        log.info("productList================================== : {} : " , productList);

        mv.addObject("productList", productList);
        mv.addObject("type",type);
        mv.setViewName("client/content/list/productList");

        log.info("[ListController] ========================================================= end");

        log.info("productList=========== {}", productList);
        System.out.println("mv========================="+mv);



        return mv;

    }
}
