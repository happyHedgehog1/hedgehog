package com.hedgehog.client.list.service;

import com.hedgehog.client.list.dto.ProductListDTO;

import java.util.List;

public interface ProductListService {

    public List<ProductListDTO> selectProductList(String type);

    public int selectTotalPageCount(String type);

}
