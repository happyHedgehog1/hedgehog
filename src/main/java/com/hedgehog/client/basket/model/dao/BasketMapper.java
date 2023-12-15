package com.hedgehog.client.basket.model.dao;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.basket.model.dto.CartSumDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BasketMapper {
//    List<CartSelectDTO> selectCartList();//장바구니 상품 조회
    List<CartSelectDTO> selectCartListByUserCode(int userCode);//usercode를 통해 장바구니 조회

    List<CartSumDTO> selectCartSum();

    void deleteCartItems(List<Integer> cartCode);


//    List<Integer> getCartInfo(int userCode);

}
