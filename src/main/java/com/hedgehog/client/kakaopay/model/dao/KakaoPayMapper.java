package com.hedgehog.client.kakaopay.model.dao;

import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.kakaopay.model.dto.OrderPayment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KakaoPayMapper {

void saveOrderDetail(int userCode, OrderPayment orderPayment);


//    void saveAllOrderInfo(OrderPayment orderPayment);
}
