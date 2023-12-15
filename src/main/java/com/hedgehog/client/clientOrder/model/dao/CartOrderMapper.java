package com.hedgehog.client.clientOrder.model.dao;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartOrderMapper {


    List<CartSelectDTO> cartOrderSelect(List<Integer> cartcheckbox);
}
