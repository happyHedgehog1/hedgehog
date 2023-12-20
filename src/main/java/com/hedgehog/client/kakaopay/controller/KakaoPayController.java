package com.hedgehog.client.kakaopay.controller;

import com.hedgehog.client.kakaopay.model.dto.ApproveResponse;
import com.hedgehog.client.kakaopay.model.dto.ReadyResponse;
import com.hedgehog.client.kakaopay.model.service.KakaoPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@SessionAttributes({"tid","order"})

public class KakaoPayController {


    private final KakaoPayService kakaoPayService;

    public KakaoPayController(KakaoPayService kakaoPayService) {
        this.kakaoPayService = kakaoPayService;
    }

    @PostMapping("/order/pay")
    @ResponseBody
    public ReadyResponse payReady(@RequestParam int totalPayPrice, Model model) {

        log.info("주문가격:"+totalPayPrice);
        // 카카오 결제 준비하기   - 결제요청 service 실행.
        log.info("========================================================> 시작? 컨트롤 ? ");
        ReadyResponse readyResponse = kakaoPayService.payReady(totalPayPrice);
        model.addAttribute("totalOrder",totalPayPrice);


//        model.addAttribute("totalOrder", totalPayPrice  );
        // 요청처리후 받아온 결재고유 번호(tid)를 모델에 저장
        model.addAttribute("tid", readyResponse.getTid());
        log.info("결재고유 번호: " + readyResponse.getTid());

        log.info("=========================================================컨트롤러 첫 리턴 ");
        return readyResponse; // 클라이언트에 보냄.(tid,next_redirect_pc_url이 담겨있음.)
    }

    @GetMapping("/order/pay/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken, @ModelAttribute("tid") String tid, Model model) {

        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
        log.info("결재고유 번호: " + tid);

        // 카카오 결재 요청하기
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);


        log.info("----------어디까지 된거냐 여긴 컨트롤러 마지막 ");

        return "redirect:/client/content/clientOrder/orderCompleted";

    }

}


