package com.hedgehog.client.kakaopay.controller;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.kakaopay.model.dto.ApproveResponse;
import com.hedgehog.client.kakaopay.model.dto.ReadyResponse;
import com.hedgehog.client.kakaopay.model.dto.OrderPayment;
import com.hedgehog.client.kakaopay.model.service.KakaoPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;


@Slf4j
@Controller
@SessionAttributes({"tid", "OrderPayment"})
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final HttpServletRequest request;

    public KakaoPayController(KakaoPayService kakaoPayService, HttpServletRequest request) {
        this.kakaoPayService = kakaoPayService;
        this.request = request;
    }


    @PostMapping("/kakao/pay")
    public @ResponseBody ReadyResponse payReady(
                                  @RequestParam String name,
                                  @RequestParam String phone,
                                  @RequestParam String email,
                                  @RequestParam(required = false) int savedPoint,
                                  @RequestParam(required = false) int originalTotalOrder,
                                  @RequestParam(required = false) int deliveryPrice,
                                  @RequestParam int AllOriginalTotalOrder,
                                  @RequestParam(required = false) int usingPoint,
                                  @RequestParam(required = false) String deliveryName,
                                  @RequestParam String deliveryPhone,
                                  @RequestParam String deliveryRequest,
                                  @RequestParam List<Integer> productCode,
                                  @RequestParam List<Integer> count,
                                  @AuthenticationPrincipal LoginDetails loginDetails,
                                  OrderPayment orderpayment,


                                  Model model) {

        LoginUserDTO loginUserDTO = loginDetails.getLoginUserDTO();
        orderpayment.setFinalPrice(AllOriginalTotalOrder);
        log.info("최종 가격은 : " + orderpayment.getFinalPrice());

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
                ,AllOriginalTotalOrder, usingPoint, deliveryName, deliveryPhone, deliveryRequest, loginDetails, productCode
                    ,count);

        orderpayment.setProductCode(productCode);
        orderpayment.setPointUsage(usingPoint);
        orderpayment.setUserCode(loginUserDTO.getUserCode());
//        여기부터 orderDtails tbl 데이터
        orderpayment.setDeliveryCharge(deliveryPrice);
        orderpayment.setFinalPrice(AllOriginalTotalOrder);
        orderpayment.setCount(count);
        orderpayment.setPointCharge(savedPoint + usingPoint);
        orderpayment.setCostPrice(originalTotalOrder);
        orderpayment.setReducedPrice(usingPoint);






        model.addAttribute("approval_url", readyResponse.getNext_redirect_pc_url());
        model.addAttribute("productCode", orderpayment.getProductCode());
        model.addAttribute("tid", readyResponse.getTid());
        model.addAttribute("userCode", orderpayment.getUserCode());
        model.addAttribute("AllOriginalTotalOrder", AllOriginalTotalOrder);
        model.addAttribute("productCode", orderpayment.getProductCode());
        model.addAttribute("OrderPayment",orderpayment);
        model.addAttribute("pointUsage", usingPoint);
//        model.addAttribute("orderCode" , orderpayment.getOrderCode());


        // 요청처리후 받아온 결재고유 번호(tid)를 모델에 저장
        log.info("결재고유 번호: " + readyResponse.getTid());
        log.info("사용한 적립금" + usingPoint);
        log.info("2사용한 적립금" + orderpayment.getPointUsage());
        log.info("유저코드" + loginUserDTO.getUserCode());
        log.info("페이먼트 제품코드 이건? " + orderpayment.getProductCode());
        log.info("제품코드 이거 나오냐 " + productCode);
        log.info("결제 예정 금액 다시 : " + AllOriginalTotalOrder);
        log.info("주문코드 나오냐?" + orderpayment.getOrderCode());
        log.info("=========================================================컨트롤러 첫 리턴 ");
        return readyResponse; // 클라이언트에 보냄.(tid,next_redirect_pc_url이 담겨있음.)


    }


    @Transactional
    @GetMapping("/kakao/pay/complete")
    public String payCompleted(
            @RequestParam("pg_token") String pgToken,
            @ModelAttribute("tid") String tid,
            @ModelAttribute("OrderPayment") OrderPayment orderpayment,
                                 Model model,
    RedirectAttributes redirectAttributes) {
        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
        log.info("결재고유 번호당: " + tid);
        log.info("주문정보: " + orderpayment);
        // 카카오 결재 요청

        model.addAttribute("tid", tid);
        model.addAttribute("pg_Token" , pgToken);
        model.addAttribute("orderPayment", orderpayment);
        model.addAttribute("usedPoint",orderpayment.getPointUsage());
        model.addAttribute("productCode", orderpayment.getProductCode());
        log.info("productCode 나오냐" + orderpayment.getProductCode());
        log.info("PointUsage 나오냐" + orderpayment.getPointUsage());
        log.info("userCode 나오내" + orderpayment.getUserCode());
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);

        log.info("주문코드 이거 나와라 : " + orderpayment.getOrderCode());//이건 주문 전이라 안나오나보다
        kakaoPayService.saveOrderDetail(orderpayment.getUserCode(), orderpayment);
//        kakaoPayService.saveAllOrderInfo(orderpayment);


        redirectAttributes.addFlashAttribute("OrderPayment", orderpayment);


        log.info("=========================================컨트롤러 conpleted ");

        return "redirect:/clientOrder/orderCompleted";

    }


}


