package com.hedgehog.client.basket.model.service;

import com.hedgehog.client.basket.model.dao.BasketMapper;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BasketServiceImpl implements BasketService {


    private final BasketMapper basketMapper;
    //MyBatis의 Mapper 인터페이스로, 데이터베이스와의 상호작용을 담당

    @Autowired
    public BasketServiceImpl(BasketMapper basketMapper) {
        this.basketMapper = basketMapper;
    }
    //생성자를 통해 바스켓매퍼주입받음

    @Override
    public List<CartSelectDTO> selectCartList() {
        return basketMapper.selectCartList();
        //selectCartList()는 바스ㅋㅖㅅ매퍼에서 selectCartList를 호출하여 데이터베이스에서
        //장바구니에 있는 상품 목록을 조회하고 반환
    }
}