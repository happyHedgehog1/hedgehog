package com.hedgehog.client.product.controller;

import com.hedgehog.client.board.model.dto.ProductReviewDTO;
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


        String star = "fa-solid fa-star";
        String halfStar = "fa-solid fa-star-half";

        List<String> result = new ArrayList<>();

        double reviewAvg = 0.0;
        if (productDetailReview.get(0) != null) {
            reviewAvg = productDetailReview.get(0).getReviewAvg();

            int integerPart = (int) reviewAvg;
            log.info("정수구하기=====" + integerPart);
            if (integerPart > 0) {

                for (int i = 0; i < integerPart; i++) {
                    result.add(star);
                }

                if (reviewAvg - integerPart >= 0) {
                    result.add(halfStar);
                }
                log.info("정수수수수=====" + integerPart);


            }

            log.info("result=====" + result);
            log.info("integerPart=====" + integerPart);
            System.out.println("reviewAvg======"+reviewAvg);
        }

        log.info("관련결과값=====" + productDetailReview);

        log.info("result=====" + result);

        log.info("마지막확인결과값=====" + productDetailReview);


        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productPrice", productPrice);
        model.addAttribute("productOption", optionNameList);
        model.addAttribute("productReview", productDetailReview);
        model.addAttribute("result",result);

        System.out.println("총결과값=====" + productDetailReview);


        return "client/content/productinfo/product";
    }


//    @GetMapping("/product/{sale}")
//    public String selectSale(@PathVariable int sale){
//
//
//
//
//        return "client/content/productinfo/product";
//
//    }

}
