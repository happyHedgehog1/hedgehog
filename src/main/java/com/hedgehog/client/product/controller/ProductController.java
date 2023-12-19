package com.hedgehog.client.product.controller;

import com.hedgehog.client.product.model.dto.ProductDetailDTO;
import com.hedgehog.client.product.model.dto.ProductTextDTO;
import com.hedgehog.client.product.model.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView selectProductDetail(@PathVariable int number , ModelAndView mv) {

        List<ProductDetailDTO> productDetail = productService.selectProductDetail(number);


        log.info("상품=== " + productDetail);

        log.info("상품텍스트=== " + productDetail.get(0).getProductText().get(0).getProductName());

        mv.addObject("productDetail", productDetail);

        mv.setViewName("client/content/productinfo/product");


        return mv;
    }

}
