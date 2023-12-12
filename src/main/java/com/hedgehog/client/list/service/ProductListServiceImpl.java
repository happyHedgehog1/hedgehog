package com.hedgehog.client.list.service;

import com.hedgehog.client.list.dao.ProductListMapper;
import com.hedgehog.client.list.dto.ProductListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductListServiceImpl implements ProductListService{

    private final ProductListMapper mapper;

    public ProductListServiceImpl(ProductListMapper mapper){

        this.mapper = mapper;

    }
    @Override
    public List<ProductListDTO> selectProductList() {

        List<ProductListDTO> productList = mapper.selectProductList();

        log.info("selectProductList =====================", productList);

        return productList;
    }


}
