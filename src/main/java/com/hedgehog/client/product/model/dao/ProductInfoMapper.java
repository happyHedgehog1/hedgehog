package com.hedgehog.client.product.model.dao;

import com.hedgehog.client.product.model.dto.ProductDetailDTO;
import com.hedgehog.client.product.model.dto.ProductDetailReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductInfoMapper {
    List<ProductDetailDTO> selectProductDetail(int number);

    List<ProductDetailReviewDTO> selectProductReview(int number);
}
