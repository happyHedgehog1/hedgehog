package com.hedgehog.client.product.model.service;

import com.hedgehog.client.product.model.dao.ProductInfoMapper;
import com.hedgehog.client.product.model.dto.ProductDetailDTO;
import com.hedgehog.client.product.model.dto.ProductTextDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductInfoMapper mapper;

    public ProductServiceImpl(ProductInfoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ProductDetailDTO> selectProductDetail(int number) {

        List<ProductDetailDTO> result = mapper.selectProductDetail(number);

        return result;
    }

}
