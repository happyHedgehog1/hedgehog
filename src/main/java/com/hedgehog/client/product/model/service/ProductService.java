package com.hedgehog.client.product.model.service;

import com.hedgehog.client.product.model.dto.ProductDetailDTO;

import java.util.List;

public interface ProductService {
    List<ProductDetailDTO> selectProductDetail(int number);

}
