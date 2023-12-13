package com.hedgehog.client.list.service;

import com.hedgehog.client.list.dto.ProductListDTO;

import java.util.List;

public interface ProductListService {

    List<ProductListDTO> selectProductList(String type);


}
