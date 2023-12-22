package com.hedgehog.client.kakaopay.model.dao;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KakaoPayMapper {


    List<CartSelectDTO> getCartByUserNo(int userCode);
}
