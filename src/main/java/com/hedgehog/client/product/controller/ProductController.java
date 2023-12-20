package com.hedgehog.client.product.controller;

import com.hedgehog.client.product.model.dto.ProductDetailDTO;
import com.hedgehog.client.product.model.dto.ProductTextDTO;
import com.hedgehog.client.product.model.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.compiler.ast.InstanceOfExpr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

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

        long productPrice = Long.parseLong(productDetail.get(0).getProductText().get(0).getProductPrice());

        Long productPriceObject = Long.valueOf(productPrice);
        log.info("상품가격 타입: " + productPriceObject.getClass().getName());
        log.info("상품가격=== " + productPrice);

        Set<String> overlapOptionName = new HashSet<>();
        for (ProductDetailDTO detail : productDetail) {
            overlapOptionName.add(detail.getProductText().get(0).getOptionName());
        }

        List<String> optionNameList = new ArrayList<>(overlapOptionName);

        log.info("옵션확인========"+ optionNameList);

        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productPrice", productPrice);
        model.addAttribute("productOption", optionNameList);

        return "client/content/productinfo/product";
    }

}
