package com.hedgehog.client.product.model.service;

import com.hedgehog.client.product.model.dto.ProductDetailDTO;
import com.hedgehog.client.product.model.dto.ProductDetailReviewDTO;

import java.util.List;

public interface ProductService {
    List<ProductDetailDTO> selectProductDetail(int number);

    List<ProductDetailReviewDTO> selectProductReview(int number);

    void addCart(String color, int productCode, int userCode);
}
