package com.hedgehog.client.product.controller;

import com.hedgehog.client.product.model.dto.ProductDetailDTO;
import com.hedgehog.client.product.model.dto.ProductDetailReviewDTO;
import com.hedgehog.client.product.model.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


@Controller
@Slf4j
@RequestMapping("/productinfo")
public class ProductController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/product/{number}")
    public String selectProductDetail(@PathVariable int number , Model model) {

        List<ProductDetailDTO> productDetail = productService.selectProductDetail(number);


        log.info("상품정보=== " + productDetail);
        log.info("상품가격== " + productDetail.get(0).getProductText().get(0).getProductPrice());

        long productPrice = Long.parseLong(productDetail.get(0).getProductText().get(0).getProductPrice());  /* 가격 확인 */



        for (ProductDetailDTO prodDetail : productDetail) {
            log.info("상품목록====" + prodDetail);



        }


        Long productPriceObject = Long.valueOf(productPrice);
        log.info("상품가격 타입: " + productPriceObject.getClass().getName());
        log.info("상품가격=== " + productPrice);

        Set<String> overlapOptionName = new HashSet<>();
        for (ProductDetailDTO detail : productDetail) {
            overlapOptionName.add(detail.getProductText().get(0).getOptionName());
        }

        List<String> optionNameList = new ArrayList<>(overlapOptionName);

        log.info("옵션확인========" + optionNameList);

        /* 리뷰 */

        List<ProductDetailReviewDTO> productDetailReview = productService.selectProductReview(number);

        log.info("들고온상품리뷰관련결과값=====" + productDetailReview);


        System.out.println("리뷰총갯===" + productDetailReview.size());  /*  null도 1로인식..  */


        System.out.println("사이즈===="+productDetailReview.size());

        System.out.println("리뷰총갯수수수===" + productDetailReview.size());


        String fullStarIcon = "fas fa-star";
        String halfStarIcon = "fa-star-half";


        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productPrice", productPrice);
        model.addAttribute("productOption", optionNameList);
        model.addAttribute("productReview", productDetailReview);
        model.addAttribute("fullStarIcon",fullStarIcon);

        System.out.println("총결과값=====" + productDetailReview);


        return "client/content/productinfo/product";
    }

}
