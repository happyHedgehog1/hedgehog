package com.hedgehog.client.basket.model.service;

import com.hedgehog.client.basket.model.dao.BasketMapper;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.basket.model.dto.CartSumDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    //생성자주입을 통해 바스켓매퍼의 구현체를 주입받고 있다.


    @Override
    public List<CartSelectDTO> selectCartList(int userCode) {
        return basketMapper.selectCartListByUserCode(userCode);

        //selectCartList()는 바스ㅋㅖㅅ매퍼에서 selectCartList를 호출하여 데이터베이스에서
        //장바구니에 있는 상품 목록을 조회하고 반환
    }




    @Override
    public List<CartSumDTO> selectCartSum() {

            return basketMapper.selectCartSum();
            //selectCartSum()은 바스켓메퍼에서 selectCartSum를 호출하여 데이터베이스에서
            //장바구니에 잇는 상품 목록을 조회해서 반환해준다.

    }

    @Override
    public int getTotalCartSum(List<CartSumDTO> cartSumDTOList) {
        int totalSum = 0;
        for (CartSumDTO cartItem : cartSumDTOList){
            int productSum = cartItem.getPrice() * cartItem.getAmount();
            totalSum += productSum;
            log.info("상품 구매 금액 : {}", productSum);
        }


        log.info("총 상품 구매 금액 : {}", totalSum);
        return totalSum;
    }

    // 반복문을 통해서 장바구니개코드값을 리스트로 가져와서 삭제해준다.
    @Override
    public void deleteCartItems(List<Integer> cartCode) {
        basketMapper.deleteCartItems(cartCode);

    }

//    @Override
//    public void deleteCartItems(List<Integer> cartCode) {
//        basketMapper.deleteCartItems(cartCode);
//    }


}
