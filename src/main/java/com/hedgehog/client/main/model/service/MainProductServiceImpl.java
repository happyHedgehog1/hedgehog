package com.hedgehog.client.main.model.service;

import com.hedgehog.client.list.dao.ProductListMapper;
import com.hedgehog.client.list.dto.ProductListDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MainProductServiceImpl implements MainProductService{


    private final ProductListMapper mapper;

    public MainProductServiceImpl(ProductListMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public List<ProductListDTO> selectBestProduct() {

        List<ProductListDTO> selectBestProduct = mapper.selectBestProduct();

        System.out.println("화킨4545=====" + selectBestProduct);

        return selectBestProduct;
    }
}
