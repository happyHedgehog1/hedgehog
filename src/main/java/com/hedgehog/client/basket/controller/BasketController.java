package com.hedgehog.client.basket.controller;


import com.hedgehog.client.basket.model.dto.BasketDTO;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.basket.model.service.BasketService;
import com.hedgehog.client.basket.model.service.BasketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/basket")
@Slf4j
public class BasketController {


    private final BasketService basketService;//서비스 주입받아서 사용
    //서비스 계층에서ㅓ 정의된 메소드 호출해서 비즈니스로직을 수행

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
//의존성 주입
    }


//    @GetMapping("/cart")
//    public ModelAndView cartselect(@ModelAttribute CartSelectDTO cartSelect) {
//
//        List<BasketDTO> cartItemList = basketServiceImpl.getCartItems(cartSelect);
//
//
//        return "client/content/basket/cart";
//    }

//    @GetMapping("/cart")
//    public ModelAndView cartSelect(@ModelAttribute CartSelectDTO cartSelect) {
//        List<BasketDTO> cartItemList = basketServiceImpl.selectCartList(cartSelect);
//
//        ModelAndView modelAndView = new ModelAndView("client/content/basket/cart");
//        modelAndView.addObject("cartItemList", cartItemList);
//
//        return modelAndView;
//    }

//    @GetMapping("/cart")
//    public String selectCartList(Model model) {//장바구니 상품 조회해서 Model에 담아서 뷰로 전달
//        List<CartSelectDTO> cartItemList = basketService.selectCartList();// 데이터 처리 로직 필요
//        model.addAttribute("cartItemList", cartItemList);
//        //그니까 서비스에서 조회해서 그거 cartItemList에 담아서 model에 추가하고 리턴된 경로를 통해 사용자에게 전달
//
//
//        log.info("log확인 메세지" );
//        return "client/content/basket/cart";
//    }

    @GetMapping("/cart")
    public String selectCartList(Model model) {

            List<CartSelectDTO> cartItemList = basketService.selectCartList();
            model.addAttribute("cartItemList", cartItemList);



        log.info("장바구니 상품 조회 완료");
        return "client/content/basket/cart";
    }



}
