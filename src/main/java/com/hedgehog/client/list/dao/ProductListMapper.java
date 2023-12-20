package com.hedgehog.client.list.dao;

import com.hedgehog.client.list.dto.ProductListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductListMapper {
    List<ProductListDTO> selectProductList(Map<String, Object> map);

    int selectTotalPageCount(String type);


}
