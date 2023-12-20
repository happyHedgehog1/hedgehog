package com.hedgehog.client.list.service;

import com.hedgehog.client.list.dao.ProductListMapper;
import com.hedgehog.client.list.dto.ProductListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProductListServiceImpl implements ProductListService{

    private final ProductListMapper mapper;

    public ProductListServiceImpl(ProductListMapper mapper){

        this.mapper = mapper;
    }

    @Override
    public int selectTotalPageCount(String type) {

        int result = mapper.selectTotalPageCount(type);

        log.info("result=================== {}" ,result );

        return result;
    }

    @Override
    public List<ProductListDTO> selectProductList(Map<String, Object> map) {

        List<ProductListDTO> productList = mapper.selectProductList(map);


        log.info("selectProductList : {}", productList);
//        log.info("type : " + type);

        return productList;
    }



}
