package com.hedgehog.client.list.dao;

import com.hedgehog.client.list.dto.ProductListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductListMapper {
    List<ProductListDTO> selectProductList(String type);

    int selectTotalPageCount(String type);
}
