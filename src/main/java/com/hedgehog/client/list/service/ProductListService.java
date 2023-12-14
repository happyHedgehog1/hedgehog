package com.hedgehog.client.list.service;

import com.hedgehog.client.list.dto.ProductListDTO;
import com.hedgehog.common.paging.SelectCriteria;

import java.util.List;
import java.util.Map;

public interface ProductListService {

    List<ProductListDTO> selectProductList( Map<String, Object> map);

    int selectTotalPageCount(String type);


}
