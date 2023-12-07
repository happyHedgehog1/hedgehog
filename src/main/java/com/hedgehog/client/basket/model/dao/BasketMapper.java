package com.hedgehog.client.basket.model.dao;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BasketMapper {
    List<CartSelectDTO> selectCartList();


}
