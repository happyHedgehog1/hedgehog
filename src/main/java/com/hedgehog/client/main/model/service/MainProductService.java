package com.hedgehog.client.main.model.service;

import com.hedgehog.client.list.dto.ProductListDTO;

import java.util.List;

public interface MainProductService {

    List<ProductListDTO> selectBestProduct();
}
