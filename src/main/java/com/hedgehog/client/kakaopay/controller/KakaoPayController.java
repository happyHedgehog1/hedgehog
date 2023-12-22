package com.hedgehog.client.kakaopay.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.kakaopay.model.dto.ApproveResponse;
import com.hedgehog.client.kakaopay.model.dto.ReadyResponse;
import com.hedgehog.client.kakaopay.model.service.KakaoPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@SessionAttributes({"tid","order"})
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final HttpServletRequest request;

    public KakaoPayController(KakaoPayService kakaoPayService, HttpServletRequest request) {
        this.kakaoPayService = kakaoPayService;
        this.request = request;
    }

    @PostMapping("/kakao/pay")
    public @ResponseBody ReadyResponse payReady(
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String phone,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String savedPoint,
                                  @RequestParam(required = false) String originalTotalOrder,
                                  @RequestParam(required = false) String deliveryPrice,
                                  @RequestParam(required = false) String AllOriginalTotalOrder,
                                  @RequestParam(required = false) String usingPoint,
                                  @RequestParam(required = false) String deliveryName,
                                  @RequestParam(required = false) String deliveryPhone,
                                  @RequestParam(required = false) String deliveryRequest,
                                  Model model) {


        // 카카오 결제 준비하기 결제요청 service 실행.
        log.info("이름 : " + name);
        log.info("이메일 : " + email);
        log.info("사용하고 남은 적립금 : " + savedPoint);
        log.info("폰번호 : "+ phone);
        log.info("상품 합계 가격 : "+ originalTotalOrder);
        log.info("배송비 : "+ deliveryPrice);
        log.info("결제 예정 금액 : " + AllOriginalTotalOrder);
        log.info("적용한 적립금 : " + usingPoint);
        log.info("받는사람 이름 : " + deliveryName);
        log.info("받는사람 번호 : " + deliveryPhone);
        log.info("배송요청사항 : " + deliveryRequest);

        log.info("==================================================== 컨트롤러 출력 시작 ");
        ReadyResponse readyResponse = kakaoPayService.payReady( name, phone, email,
                savedPoint, originalTotalOrder, deliveryPrice
                ,AllOriginalTotalOrder,usingPoint, deliveryName, deliveryPhone, deliveryRequest

        );

        model.addAttribute("tid", readyResponse.getTid());


        // 요청처리후 받아온 결재고유 번호(tid)를 모델에 저장
        log.info("결재고유 번호: " + readyResponse.getTid());
        log.info("결제 예정 금액 다시 : " + AllOriginalTotalOrder);
//        log.info("이건 왜 안들어와 다시 : " + readyResponse.getAllOriginalTotalOrder());
        log.info("이건" + readyResponse.getPartner_order_id());


//        readyResponse.setAllOriginalTotalOrder(String.valueOf(AllOriginalTotalOrder));
        model.addAttribute("AllOriginalTotalOrder", AllOriginalTotalOrder);



        log.info("=========================================================컨트롤러 첫 리턴 ");
        return readyResponse; // 클라이언트에 보냄.(tid,next_redirect_pc_url이 담겨있음.)

    }

    @GetMapping("/order/pay/complete")
    public String payCompleted(
                                @RequestParam("pg_token") String pgToken,
                               @ModelAttribute("tid") String tid,
                               Model model) {

//                               @RequestParam(value = "AllOriginalTotalOrder", required = false) int AllOriginalTotalOrder,

        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
        log.info("결재고유 번호: " + tid);

        // 카카오 결재 요청하기
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);
//         session = request.getSession();
//        session.setAttribute("AllOriginalTotalOrder", AllOriginalTotalOrder);


//        model.addAttribute("AllOriginalTotalOrder", AllOriginalTotalOrder);
        model.addAttribute("주문번호", tid);


        log.info("=========================================컨트롤러 conpleted ");

        return "redirect:/client/content/clientOrder/orderCompleted";

    }

}


