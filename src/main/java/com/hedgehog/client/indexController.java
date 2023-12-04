package com.hedgehog.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class indexController {

    /**
     * 기본 요청 시 페이지 이동을 위한 메소드
     */
    @GetMapping
    public String root(){
        return "index";
    }

}
