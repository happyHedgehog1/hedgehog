package com.hedgehog.client.product.model.service;

import com.hedgehog.client.product.model.dto.ProductDetailDTO;
import com.hedgehog.client.product.model.dto.ProductTextDTO;

import java.util.List;

public interface ProductService {
    List<ProductDetailDTO> selectProductDetail(int number);

}
